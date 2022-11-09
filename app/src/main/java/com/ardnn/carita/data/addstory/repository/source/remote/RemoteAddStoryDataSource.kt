package com.ardnn.carita.data.addstory.repository.source.remote

import com.ardnn.carita.data.addstory.repository.source.AddStoryDataSource
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteAddStoryDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : AddStoryDataSource {

    override fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Observable<AddStoryResponse> = api.postStory(token, file, description)

}