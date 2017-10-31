package com.smith.ufc.data.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.smith.ufc.Marvel
import com.smith.ufc.data.repositories.character.CharacterRepository
import com.smith.ufc.data.repositories.comic.ComicRepository
import com.smith.ufc.data.di.MarvelModule
import com.smith.ufc.data.service.MarvelDataSource

import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Charlton on 10/27/17.
 */

open class BaseActivity : AppCompatActivity() {

    @field:[Inject Named(MarvelModule.NAMED_NETWORK)]
    lateinit var source: MarvelDataSource

    @field:[Inject]
    lateinit var comicDb: ComicRepository

    @field:[Inject]
    lateinit var characterDb: CharacterRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Marvel.instance.component!!.inject(this)
    }
}
