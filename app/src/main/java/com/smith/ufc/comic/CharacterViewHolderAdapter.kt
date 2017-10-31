package com.smith.ufc.comic

import com.smith.ufc.data.base.BaseAdapter
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.models.MarvelComic
import java8.util.stream.Collectors
import java8.util.stream.StreamSupport

/**
 * Created by Charlton on 10/31/17.
 */
class CharacterViewHolderAdapter : BaseAdapter<CharacterViewHolderAdapter.CharacterView, MarvelCharacter>() {

    override fun getItemId(): MutableList<Int> = StreamSupport.stream(super.getItems()).map { r -> r.id  }.collect(Collectors.toList())

    override fun onBindRepositoryRowViewAtPosition(position: Int, view: CharacterView) {
        val repo = getItem(position)
        view.setImage(repo.thumbnail.toString())
        view.setTitle(repo.name)
    }


    interface CharacterView : BaseAdapter.BaseView {

        fun setTitle(title: String)

        fun setImage(description: String)


    }

}
