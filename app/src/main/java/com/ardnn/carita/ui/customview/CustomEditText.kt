package com.ardnn.carita.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

abstract class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    protected fun init() {
        inputType = getTypeInput()
        compoundDrawablePadding = 16

        setCompoundDrawablesWithIntrinsicBounds(getLeftIcon(), null, null, null)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // no implementation
            }

            override fun onTextChanged(input: CharSequence, p1: Int, p2: Int, p3: Int) {
                errorCondition(input)
            }

            override fun afterTextChanged(p0: Editable?) {
                // no implementation
            }
        })
    }

    abstract fun getLeftIcon(): Drawable?

    abstract fun getTypeInput(): Int

    abstract fun errorCondition(input: CharSequence)
}