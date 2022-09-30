package com.ardnn.carita.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.ardnn.carita.R

class PasswordEditText : AppCompatEditText {

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

    private fun init() {
        inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        compoundDrawablePadding = 16

        val passwordIcon = ContextCompat.getDrawable(context, R.drawable.ic_lock) as Drawable
        setCompoundDrawablesWithIntrinsicBounds(passwordIcon, null, null, null)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // no implementation
            }

            override fun onTextChanged(input: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (input.length in 1..5) {
                    error = "Password must be at least six characters"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // no implementation
            }
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        transformationMethod = PasswordTransformationMethod.getInstance()
    }
}