package com.ardnn.carita.data.addstory.repository.source.remote

import com.ardnn.carita.data.addstory.repository.source.AddStoryDataSource
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteAddStoryDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : AddStoryDataSource {

    override fun postStory(
        file: MultipartBody.Part,
        description: RequestBody
    ): Flow<AddStoryResponse> {
        return flow {
            emit(api.postStory(file, description))
        }
    }
}