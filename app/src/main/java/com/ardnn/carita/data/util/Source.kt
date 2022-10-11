package com.ardnn.carita.data.util

import androidx.annotation.StringDef

@StringDef(
    Source.LOCAL,
    Source.REMOTE
)
annotation class Source {

    companion object {

        const val LOCAL = "local"
        const val REMOTE = "remote"
    }
}
