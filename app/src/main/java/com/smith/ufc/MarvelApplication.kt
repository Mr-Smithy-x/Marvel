package com.smith.ufc

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

/**
 * Created by Charlton on 10/27/17.
 */

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Realm.init(this)
        var config = RealmConfiguration.Builder().name("marvel.realm").deleteRealmIfMigrationNeeded().schemaVersion(1).build()
        Realm.setDefaultConfiguration(config)
    }

}
