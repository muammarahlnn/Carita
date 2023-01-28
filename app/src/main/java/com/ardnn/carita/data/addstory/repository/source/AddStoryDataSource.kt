package com.ardnn.carita.data.addstory.repository.source

import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoryDataSource {

    fun postStory(
        file: MultipartBody.Part,
        description: RequestBody
    ): Flow<AddStoryResponse>
}