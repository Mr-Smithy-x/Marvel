package com.smith.ufc.character

import com.smith.ufc.data.base.RealmSpecification
import com.smith.ufc.data.models.MarvelCharacter

import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by Charlton on 10/31/17.
 */

class CharacterSpecification : RealmSpecification<MarvelCharacter> {

    private var name: String? = null
    private var id = -1

    constructor(id: Int) {
        this.id = id
    }

    constructor(name: String) {
        this.name = name
    }


    override fun toRealmResults(realm: Realm): RealmResults<MarvelCharacter> = when {
        id > -1 -> realm.where(MarvelCharacter::class.java)
                .equalTo(MarvelCharacter.Fields.ID, id)
                .findAll()
        name != null -> realm.where(MarvelCharacter::class.java)
                .beginsWith(MarvelCharacter.Fields.NAME, name, Case.INSENSITIVE)
                .findAll()
        else -> realm.where(MarvelCharacter::class.java)
                .findAll()
    }
}
