package com.smith.ufc.data.repositories

import com.smith.ufc.data.base.BaseSpecification
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * Created by Charlton on 10/31/17.
 */

interface RealmSpecification<T : RealmModel> : BaseSpecification {
    fun toRealmResults(realm: Realm): RealmResults<T>
}
