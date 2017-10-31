package com.smith.ufc.data.base

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * Created by Charlton on 10/31/17.
 */

interface RealmSpecification<T : RealmModel> : Specification {
    fun toRealmResults(realm: Realm): RealmResults<T>
}
