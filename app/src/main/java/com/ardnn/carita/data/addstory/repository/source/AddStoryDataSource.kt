package com.ardnn.carita.data.addstory.repository.source

import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoryDataSource {

    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Observable<AddStoryResponse>
}