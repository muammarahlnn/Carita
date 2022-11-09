package com.ardnn.carita.data.addstory.repository

import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoryRepository {

    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Observable<AddStoryResponse>
}