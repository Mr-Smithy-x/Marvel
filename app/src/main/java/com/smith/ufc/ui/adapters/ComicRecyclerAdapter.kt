package com.smith.ufc.ui.adapters

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smith.ufc.R
import com.smith.ufc.comic.ComicActivity
import com.smith.ufc.data.models.MarvelComic
import com.smith.ufc.comic.presenters.ComicRecyclerPresenter


/**
 * Created by Charlton on 10/26/17.
 */

class ComicRecyclerAdapter(val repository: ComicRecyclerPresenter) : RecyclerView.Adapter<ComicRecyclerAdapter.ComicViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder = ComicViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        repository.onBindRepositoryRowViewAtPosition(position, holder)
    }

    override fun getItemId(position: Int): Long = repository.getItem(position).id.toLong()


    override fun getItemViewType(position: Int): Int = R.layout.holder_comic_view

    override fun getItemCount(): Int = repository.repositoriesRowsCount

    /**
     * Created by Charlton on 10/26/17.
     */

    inner class ComicViewHolder(view: View) : RecyclerView.ViewHolder(view), ComicRecyclerPresenter.ComicViewHolder {

        private val title: AppCompatTextView = view.findViewById(R.id.comic_title)
        private val image: AppCompatImageView = view.findViewById(R.id.comic_image)

        init {
            itemView.setOnClickListener { v -> v.context.startActivity(ComicActivity.newInstance(v.context, repository.getItem(adapterPosition).id)) }
        }

        override fun setTitle(title: String) {
            this.title.text = title
        }

        override fun setImage(description: String) {
            Glide.with(itemView.context).asDrawable().load(description).into(image)
        }
    }

    fun setData(comics: List<MarvelComic?>) {
        repository.setData(comics)
        notifyDataSetChanged()
    }

}
