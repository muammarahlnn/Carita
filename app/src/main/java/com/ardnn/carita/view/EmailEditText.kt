package com.ardnn.carita.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.AttributeSet
import android.util.Patterns
import androidx.core.content.ContextCompat
import com.ardnn.carita.R

class EmailEditText : CustomEditText {

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

    override fun getLeftIcon(): Drawable? =
        ContextCompat.getDrawable(context, R.drawable.ic_email)

    override fun getTypeInput(): Int =
        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

    override fun errorCondition(input: CharSequence) {
        if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            error = context.getString(R.string.email_not_valid)
        }
    }
}