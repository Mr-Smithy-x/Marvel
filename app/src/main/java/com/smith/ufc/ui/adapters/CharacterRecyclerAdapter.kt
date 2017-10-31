package com.smith.ufc.ui.adapters

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smith.ufc.R
import com.smith.ufc.character.CharacterActivity
import com.smith.ufc.comic.CharacterViewHolderAdapter
import com.smith.ufc.comic.ComicActivity
import com.smith.ufc.data.models.MarvelCharacter


/**
 * Created by Charlton on 10/26/17.
 */

class CharacterRecyclerAdapter(val repository: CharacterViewHolderAdapter) : RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder = CharacterViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        repository.onBindRepositoryRowViewAtPosition(position, holder)
    }

    override fun getItemId(position: Int): Long = repository.getItem(position).id.toLong()


    override fun getItemViewType(position: Int): Int = R.layout.holder_character_view

    override fun getItemCount(): Int = repository.repositoriesRowsCount


    /**
     * Created by Charlton on 10/26/17.
     */

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view), CharacterViewHolderAdapter.CharacterView {

        private val title: AppCompatTextView = view.findViewById(R.id.character_name)
        private val image: AppCompatImageView = view.findViewById(R.id.character_image)

        init {
            itemView.setOnClickListener { v -> v.context.startActivity(CharacterActivity.newInstance(v.context, repository.getItem(adapterPosition).id)) }
        }

        override fun setTitle(title: String) {
            this.title.text = title
        }

        override fun setImage(description: String) {
            Glide.with(itemView.context).asDrawable().load(description).into(image)
        }
    }

    fun setData(comics: List<MarvelCharacter?>) {
        repository.setData(comics)
        notifyDataSetChanged()
    }

}
