package com.smith.ufc.data.base;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by Charlton on 10/31/17.
 */

public interface RealmSpecification<T extends RealmModel> extends Specification {
    RealmResults<T> toRealmResults(Realm realm);
}
