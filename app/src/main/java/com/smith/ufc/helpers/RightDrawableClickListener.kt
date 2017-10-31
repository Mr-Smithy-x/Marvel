package com.smith.ufc.helpers

import android.view.MotionEvent
import android.view.View
import android.widget.EditText

/**
 * Created by Charlton on 10/26/17.
 *
 * When right drawable is clicked/touched, the following action will occur
 */
abstract class RightDrawableClickListener(private val editText: EditText) : View.OnTouchListener {


    abstract fun onClick(editText: EditText)



    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (editText.compoundDrawables[2] != null) {
                if (event.x >= editText.right - editText.left - editText.compoundDrawables[2].bounds.width()) {
                    onClick(editText)
                    return true
                }
            }
        }
        return false
    }
}
