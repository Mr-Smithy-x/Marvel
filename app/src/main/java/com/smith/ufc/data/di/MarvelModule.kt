package com.smith.ufc.data.di

import com.smith.ufc.character.CharacterContract
import com.smith.ufc.character.CharacterRepository
import com.smith.ufc.character.CharacterRepositoryImpl
import com.smith.ufc.comic.MarvelComicInteractor
import com.smith.ufc.character.MarvelCharacterInteractor
import com.smith.ufc.comic.ComicContract
import com.smith.ufc.comic.ComicRepository
import com.smith.ufc.comic.ComicRepositoryImpl
import com.smith.ufc.data.service.MarvelDataSource
import com.smith.ufc.data.service.MarvelNetworkSource

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Charlton on 10/27/17.
 */
@Module
class MarvelModule {

    private val characterInteractor: CharacterContract.Interactor
    private val comicInteractor: ComicContract.Interactor


    init {
        characterInteractor = MarvelCharacterInteractor()
        comicInteractor = MarvelComicInteractor()
    }

    @Provides
    @Singleton
    fun providesCharacterInteractor(): CharacterContract.Interactor = characterInteractor


    @Provides
    @Singleton
    fun providesComicInteractor(): ComicContract.Interactor = comicInteractor

    @Provides
    @Singleton
    @Named(NAMED_BACKGROUND)
    fun providesBackgroundScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Named(NAMED_MAIN_SCHEDULER)
    fun providesMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(NAMED_NETWORK)
    fun providesSource(@Named(NAMED_BACKGROUND) background: Scheduler, @Named(NAMED_MAIN_SCHEDULER) main: Scheduler): MarvelDataSource = MarvelNetworkSource(background, main)

    @Provides
    @Singleton
    fun providerComicRepo(@Named(NAMED_NETWORK) source: MarvelDataSource): ComicRepository = ComicRepositoryImpl(RealmConfiguration.Builder().name("marvel.realm").deleteRealmIfMigrationNeeded().schemaVersion(1).build(), source)

    @Provides
    @Singleton
    fun providesCharacterRepo(@Named(NAMED_NETWORK) source: MarvelDataSource): CharacterRepository = CharacterRepositoryImpl(RealmConfiguration.Builder().name("marvel.realm").deleteRealmIfMigrationNeeded().schemaVersion(1).build(), source)



    companion object {

        const val NAMED_NETWORK = "NETWORK_SOURCE"
        const val NAMED_BACKGROUND = "BACKGROUND_SCHEDULER"
        const val NAMED_MAIN_SCHEDULER = "MAIN_SCHEDULER"
    }

}
