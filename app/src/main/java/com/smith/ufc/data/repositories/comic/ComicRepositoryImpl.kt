package com.smith.ufc.data.repositories.comic

import android.support.annotation.NonNull
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.models.MarvelComic
import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.MarvelResponse
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.data.service.MarvelComicDataSource
import io.reactivex.Observable
import io.realm.*
import java8.util.stream.StreamSupport

/**
 * Created by Charlton on 10/31/17.
 */

class ComicRepositoryImpl(private val realmConfiguration: RealmConfiguration, private val mComicDataSource: MarvelComicDataSource) : ComicRepository {


    override fun add(item: MarvelComic) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

    override fun add(items: List<MarvelComic>) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(items) }
    }

    override fun update(item: MarvelComic) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

    override fun remove(item: MarvelComic) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { item.deleteFromRealm() }
    }

    override fun query(specification: ComicSpecification): List<MarvelComic> {
        val realm = Realm.getInstance(realmConfiguration)
        return specification.toRealmResults(realm)
    }


    override fun getCharacterComics(id: Int): Observable<MarvelResponse<MarvelData<MarvelComicList>>> =
            mComicDataSource.getCharacterComics(id).map({ response ->
                run {
                    add(response.data.results)
                    return@map offlineCharacterComics(id)
                }
            }).onErrorReturn { offlineCharacterComics(id) }.doOnNext({ r ->
                run {
                    Realm.getDefaultInstance().executeTransaction { realm -> realm.copyToRealmOrUpdate(r.data.results) }
                    StreamSupport.stream(search("id", id, MarvelCharacter::class.java)?.findFirst()?.comics?.nulledSummary).forEach { nulledComic -> getComic(nulledComic.resourceId).subscribe({ o -> add(o.data.results) }, {}, {}) }
                }
            })


    private fun offlineCharacterComics(id: Int): MarvelResponse<MarvelData<MarvelComicList>> {
        val data = MarvelResponse<MarvelData<MarvelComicList>>(200)
        data.data = MarvelData(MarvelComicList())
        val search = search("id", id, MarvelCharacter::class.java)?.findFirst()
        data.data.results.addAll(search?.comics?.comics!!)
        return data
    }

    override fun getComics(name: String): Observable<MarvelResponse<MarvelData<MarvelComicList>>> =
            mComicDataSource.getComics(name).onErrorReturn { offlineComics(name) }.doOnNext({next -> add(next.data.results) })

    override fun getComic(id: Int): Observable<MarvelResponse<MarvelData<MarvelComicList>>> =
            mComicDataSource.getComic(id).onErrorReturn { offlineComic(id) }.doOnNext({next -> add(next.data.results) })

    override val comics: Observable<MarvelResponse<MarvelData<MarvelComicList>>>
        get() = mComicDataSource.comics.onErrorReturn { offlineComics(null) }.doOnNext({next -> add(next.data.results) })

    private fun offlineComic(id: Int?): MarvelResponse<MarvelData<MarvelComicList>> {
        val data = MarvelResponse<MarvelData<MarvelComicList>>(200)
        data.data = MarvelData(MarvelComicList())
        val search = search("id", id, MarvelComic::class.java)!!.findAll()
        data.data.results.addAll(search)
        return data
    }


    private fun offlineComics(title: String?): MarvelResponse<MarvelData<MarvelComicList>> {
        val data = MarvelResponse<MarvelData<MarvelComicList>>(200)
        data.data = MarvelData(MarvelComicList())
        val search = search("title", title, MarvelComic::class.java)!!.findAll()
        data.data.results.addAll(search)
        return data
    }


    private fun <T : RealmModel?> search(field: String?, content: Any?, @NonNull clazz: Class<T>?): RealmQuery<T>? {
        val where = Realm.getDefaultInstance().where(clazz!!)
        if (field == null || content == null) return where
        return when (content) {
            is String -> where.beginsWith(field, content, Case.INSENSITIVE)
            is Int -> where.equalTo(field, content.toInt())
            else -> where
        }
    }

}

