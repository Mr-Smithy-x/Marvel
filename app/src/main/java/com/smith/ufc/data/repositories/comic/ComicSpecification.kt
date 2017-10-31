package com.smith.ufc.data.repositories.comic

import com.smith.ufc.data.base.RealmSpecification
import com.smith.ufc.data.models.MarvelComic
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by Charlton on 10/31/17.
 */

class ComicSpecification : RealmSpecification<MarvelComic> {

    private var name: String? = null
    private var id = -1

    constructor(id: Int) {
        this.id = id
    }

    constructor(name: String) {
        this.name = name
    }


    override fun toRealmResults(realm: Realm): RealmResults<MarvelComic> = when {
        id > -1 -> realm.where(MarvelComic::class.java)
                .equalTo(MarvelComic.Fields.ID, id)
                .findAll()
        name != null -> realm.where(MarvelComic::class.java)
                .beginsWith(MarvelComic.Fields.NAME, name, Case.INSENSITIVE)
                .findAll()
        else -> realm.where(MarvelComic::class.java)
                .findAll()
    }
}
