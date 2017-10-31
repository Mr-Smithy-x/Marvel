package com.smith.ufc.character

import android.support.annotation.NonNull
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.models.MarvelComic
import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.MarvelResponse
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.service.MarvelCharacterDataSource
import io.reactivex.Observable
import io.realm.*

/**
 * Created by Charlton on 10/31/17.
 */

class CharacterRepositoryImpl(private val realmConfiguration: RealmConfiguration, private val mCharacterDataSource: MarvelCharacterDataSource) : CharacterRepository {


    override fun add(item: MarvelCharacter) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

    override fun add(items: List<MarvelCharacter>) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(items) }
    }

    override fun update(item: MarvelCharacter) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

    override fun remove(item: MarvelCharacter) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { realm1 -> item.deleteFromRealm() }
    }

    override fun query(specification: CharacterSpecification): List<MarvelCharacter> {
        val realm = Realm.getInstance(realmConfiguration)
        return specification.toRealmResults(realm)
    }


    private fun offlineComicCharacters(id: Int): MarvelResponse<MarvelData<MarvelCharacterList>> {
        val data = MarvelResponse<MarvelData<MarvelCharacterList>>(200)
        data.data = MarvelData(MarvelCharacterList())
        val search = search("id", id, MarvelComic::class.java)!!.findFirst()
        data.data.results.addAll(search?.characters?.characters!!)
        return data
    }

    override fun getComicCharacters(id: Int): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> =
            mCharacterDataSource.getComicCharacters(id).onErrorReturn { offlineComicCharacters(id) }.doOnNext({next -> add(next.data.results) })


    override fun getCharacter(id: Int): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> =
            mCharacterDataSource.getCharacter(id).onErrorReturn { offlineCharacter(id) }.doOnNext({next -> add(next.data.results) })

    override val characters: Observable<MarvelResponse<MarvelData<MarvelCharacterList>>>
        get() = mCharacterDataSource.characters.onErrorReturn { offlineCharacters(null) }.doOnNext({next -> add(next.data.results) })


    override fun getCharacters(name: String): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> =
            mCharacterDataSource.getCharacters(name).onErrorReturn { offlineCharacters(name) }.doOnNext({next -> add(next.data.results) })


    private fun offlineCharacter(id: Int?): MarvelResponse<MarvelData<MarvelCharacterList>> {
        val data = MarvelResponse<MarvelData<MarvelCharacterList>>(200)
        data.data = MarvelData(MarvelCharacterList())
        val search = search("id", id, MarvelCharacter::class.java)!!.findAll()
        data.data.results.addAll(search)
        return data
    }


    private fun offlineCharacters(title: String?): MarvelResponse<MarvelData<MarvelCharacterList>> {
        val data = MarvelResponse<MarvelData<MarvelCharacterList>>(200)
        data.data = MarvelData(MarvelCharacterList())
        val search = search("name", title, MarvelCharacter::class.java)!!.findAll()
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
