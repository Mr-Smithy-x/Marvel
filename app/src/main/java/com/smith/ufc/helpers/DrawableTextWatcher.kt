package com.smith.ufc.helpers

import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.smith.ufc.R

/**
 * Created by Charlton on 10/26/17.
 *
 * If text > 0 show right drawable
 */
class DrawableTextWatcher(private val editText: EditText) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable) =
            if (s.isNotEmpty())
                editText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(editText.context, R.drawable.ic_search_alt), null, ContextCompat.getDrawable(editText.context, R.drawable.ic_close), null)
            else
                editText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(editText.context, R.drawable.ic_search_alt), null, null, null)

}
