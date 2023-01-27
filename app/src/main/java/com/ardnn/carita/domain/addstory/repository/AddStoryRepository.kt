package com.ardnn.carita.domain.addstory.repository

import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoryRepository {

    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Flow<AddStoryResponse>
}