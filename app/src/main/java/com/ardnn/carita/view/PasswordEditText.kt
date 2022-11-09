package com.ardnn.carita.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.ardnn.carita.R

class PasswordEditText : CustomEditText {

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

    override fun getLeftIcon(): Drawable =
        ContextCompat.getDrawable(context, R.drawable.ic_lock) as Drawable


    override fun getTypeInput(): Int =
        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD


    override fun errorCondition(input: CharSequence) {
        if (input.length in 1..5) {
            error = context.getString(R.string.password_not_valid)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        transformationMethod = PasswordTransformationMethod.getInstance()
    }
}