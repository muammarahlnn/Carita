package com.ardnn.carita.data.util

import androidx.annotation.StringDef

@StringDef(
    Source.LOCAL
)
annotation class Source {

    companion object {

        const val LOCAL = "local"
    }
}
