package com.ardnn.carita.data.main.repository.source.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var userId: String? = null,
    var name: String? = null,
    var token: String? = null
) : Parcelable