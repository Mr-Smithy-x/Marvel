package com.smith.ufc

import com.smith.ufc.data.di.DaggerMarvelComponent
import com.smith.ufc.data.di.MarvelComponent
import com.smith.ufc.data.di.MarvelModule

/**
 * Created by Charlton on 10/27/17.
 */

class Marvel private constructor() {
    private var module: MarvelModule? = MarvelModule()
    var component: MarvelComponent? = DaggerMarvelComponent.builder().marvelModule(module).build()


    companion object {
        val instance = Marvel()
    }
}
