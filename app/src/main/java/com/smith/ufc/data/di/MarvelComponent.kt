package com.smith.ufc.data.di

import com.smith.ufc.MarvelApplication
import com.smith.ufc.character.presenters.MarvelCharacterPresenter
import com.smith.ufc.comic.presenters.MarvelComicPresenter
import com.smith.ufc.data.base.BaseActivity
import com.smith.ufc.data.base.BaseFragment
import com.smith.ufc.data.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Charlton on 10/27/17.
 */

@Singleton
@Component(modules = [(com.smith.ufc.data.di.MarvelModule::class)])
interface MarvelComponent {

    fun inject(baseViewModel: BaseViewModel)

    fun inject(presenter: MarvelCharacterPresenter)

    fun inject(presenter: MarvelComicPresenter)

    fun inject(application: MarvelApplication)

    fun inject(activity: BaseActivity)

    fun inject(fragment: BaseFragment)

}
