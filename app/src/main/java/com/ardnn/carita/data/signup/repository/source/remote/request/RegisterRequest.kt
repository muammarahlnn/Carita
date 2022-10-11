package com.ardnn.carita.data.signup.repository.source.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String
)