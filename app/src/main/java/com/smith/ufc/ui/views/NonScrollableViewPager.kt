package com.smith.ufc.ui.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by Charlton on 10/28/17.
 */

class NonScrollableViewPager : ViewPager {

    private var pagingEnabled: Boolean = false

    constructor(context: Context) : super(context) {
        this.pagingEnabled = false
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.pagingEnabled = false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean =
            this.pagingEnabled && super.onTouchEvent(event)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean =
            this.pagingEnabled && super.onInterceptTouchEvent(event)


    fun setPagingEnabled(enabled: Boolean) {
        this.pagingEnabled = enabled
    }
}
