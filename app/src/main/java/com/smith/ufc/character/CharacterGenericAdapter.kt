package com.smith.ufc.character

import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.base.BaseAdapter
import java8.util.stream.Collectors
import java8.util.stream.StreamSupport

/**
 * Created by Charlton on 10/27/17.
 */

class CharacterGenericAdapter : BaseAdapter<CharacterGenericAdapter.CharacterGenericView, MarvelCharacter>() {

    override fun getItemId(): MutableList<Int> = StreamSupport.stream(super.getItems()).map { r -> r.id  }.collect(Collectors.toList())


    override fun onBindRepositoryRowViewAtPosition(position: Int, view: CharacterGenericView) {
        val character = getItem(position)
        view.setTitle(character.name)
        val latest_comics = if (character.comics != null && character.comics.items.size > 0) character.comics.items[0]?.name else "No Comics"
        view.setDescription(latest_comics!!)
        view.setImage(character.thumbnail.toString())
    }

    interface CharacterGenericView : BaseAdapter.BaseView {

        fun setTitle(title: String)

        fun setDescription(description: String)

        fun setImage(button: String)

    }


}
