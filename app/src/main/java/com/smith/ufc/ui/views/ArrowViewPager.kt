package com.smith.ufc.ui.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.smith.ufc.R

/**
 * Created by Charlton on 10/28/17.
 */

class ArrowViewPager : LinearLayout {


    var mViewPager: NonScrollableViewPager? = null


    public constructor(context: Context) : super(context) {
        initialize(context, null, 0);
    }

    public constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context, attrs, 0)
    }

    public constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style) {
        initialize(context, attrs, style)
    }

    fun getViewPager(): NonScrollableViewPager = mViewPager!!


    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val inflate = View.inflate(context, R.layout.view_arrow_viewpager, this)
        mViewPager = inflate.findViewById(R.id.arrow_non_view_pager)
        val mBackArrow = inflate.findViewById<View>(R.id.arrow_back)
        val mNextArrow = inflate.findViewById<View>(R.id.arrow_front)

        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                val adapter = mViewPager!!.adapter ?: return@onPageScrolled
                when (position) {
                    0 -> mBackArrow.visibility = View.INVISIBLE
                    adapter.count - 1 -> mNextArrow.visibility = View.INVISIBLE
                    else -> {
                        mBackArrow.visibility = View.VISIBLE
                        mNextArrow.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                val adapter = mViewPager!!.adapter ?: return@onPageSelected
                when (position) {
                    0 -> mBackArrow.visibility = View.INVISIBLE
                    adapter.count - 1 -> mNextArrow.visibility = View.INVISIBLE
                    else -> {
                        mBackArrow.visibility = View.VISIBLE
                        mNextArrow.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        mBackArrow.setOnClickListener {
            val position = mViewPager!!.currentItem
            mViewPager!!.setCurrentItem(position - 1, true)
        }
        mNextArrow.setOnClickListener {
            val position = mViewPager!!.currentItem
            mViewPager!!.setCurrentItem(position + 1, true)
        }
        invalidate()
    }

}
