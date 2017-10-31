package com.smith.ufc.ui.adapters

import android.support.v4.view.PagerAdapter
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smith.ufc.R
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.character.CharacterGenericAdapter
import com.smith.ufc.character.CharacterActivity
import com.willowtreeapps.spruce.Spruce
import com.willowtreeapps.spruce.animation.DefaultAnimations
import com.willowtreeapps.spruce.sort.DefaultSort
import java.util.*

/**
 * Created by Charlton on 10/27/17.
 */

class CharacterAdapter(val repository: CharacterGenericAdapter) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate = LayoutInflater.from(container.context).inflate(R.layout.holder_image_view, container, false)
        repository.onBindRepositoryRowViewAtPosition(position, SeriesViewHolder(repository.getItem(position).id, inflate))
        Spruce.SpruceBuilder(inflate as ViewGroup?).sortWith(DefaultSort(100L)).animateWith(DefaultAnimations.growAnimator(inflate,400)).start()
        container.addView(inflate)
        return inflate
    }

    fun setData(characters: ArrayList<MarvelCharacter>) {
        repository.setData(characters)
        notifyDataSetChanged()
    }
    fun setData(data: List<MarvelCharacter?>) {
        repository.setData(data)
        notifyDataSetChanged()
    }


    override fun getItemPosition(`object`: Any?): Int = POSITION_NONE

    override fun getCount(): Int = repository.repositoriesRowsCount

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    /**
     * Created by Charlton on 10/26/17.
     */

    class SeriesViewHolder internal constructor(id:Int, itemView: View) : CharacterGenericAdapter.CharacterGenericView {

        private var title: AppCompatTextView = itemView.findViewById(R.id.character_title)
        private var description: AppCompatTextView = itemView.findViewById(R.id.character_desc)
        private var image: AppCompatImageView = itemView.findViewById(R.id.character_image)
        private var button: AppCompatButton = itemView.findViewById(R.id.character_read_more_btn)

        init {
            button.setOnClickListener { v ->
                run {
                    v.context.startActivity(CharacterActivity.newInstance(v.context, id))
                }
            }
        }

        override fun setTitle(title: String) {
            this.title.text = title
        }

        override fun setDescription(description: String) {
            this.description.text = description
        }

        override fun setImage(button: String) {
            Glide.with(image).asDrawable().load(button).into(image)
        }

    }
}
