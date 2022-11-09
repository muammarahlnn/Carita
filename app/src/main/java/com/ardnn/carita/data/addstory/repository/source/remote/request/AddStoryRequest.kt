package com.ardnn.carita.data.addstory.repository.source.remote.request

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class AddStoryRequest(
    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("photo")
    val photo: MultipartBody.Part
)