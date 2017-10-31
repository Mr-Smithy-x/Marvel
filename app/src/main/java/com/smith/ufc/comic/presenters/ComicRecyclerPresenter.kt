package com.smith.ufc.comic.presenters

import com.smith.ufc.data.base.BaseAdapterPresenter
import com.smith.ufc.data.models.MarvelComic
import java8.util.stream.Collectors
import java8.util.stream.StreamSupport

/**
 * Created by Charlton on 10/27/17.
 */

class ComicRecyclerPresenter : BaseAdapterPresenter<ComicRecyclerPresenter.ComicViewHolder, MarvelComic>() {

    override fun getItemId(): MutableList<Int> = StreamSupport.stream(super.getItems()).map { r -> r.id  }.collect(Collectors.toList())

    override fun onBindRepositoryRowViewAtPosition(position: Int, view: ComicViewHolder) {
        val repo = getItem(position)
        view.setImage(repo.thumbnail.toString())
        view.setTitle(repo.title)
    }


    interface ComicViewHolder : BaseAdapterPresenter.BaseView {

        fun setTitle(title: String)

        fun setImage(description: String)

    }

}
