package com.smith.ufc.ui.manager

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.willowtreeapps.spruce.Spruce
import com.willowtreeapps.spruce.animation.DefaultAnimations
import com.willowtreeapps.spruce.sort.DefaultSort

/**
 * Created by Charlton on 10/30/17.
 */

class AnimatedGridLayoutManager : GridLayoutManager {

    private var recycler: RecyclerView? = null


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

    constructor(context: Context, spanCount: Int) : super(context, spanCount) {}

    constructor(context: Context, spanCount: Int, orientation: Int, reverseLayout: Boolean) : super(context, spanCount, orientation, reverseLayout) {}

    fun setRecycler(recycler: RecyclerView) {
        this.recycler = recycler
    }

    fun animateSpruce() {
        if (recycler != null) {
            Spruce.SpruceBuilder(recycler).sortWith(DefaultSort(/*interObjectDelay=*/100L)).animateWith(DefaultAnimations.growAnimator(recycler, /*duration=*/400)).start()
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        if (state.didStructureChange()) {
            animateSpruce()
        }
    }
}
