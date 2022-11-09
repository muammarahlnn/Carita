package com.ardnn.carita.data.addstory.repository

import com.ardnn.carita.data.addstory.repository.source.AddStoryDataFactory
import com.ardnn.carita.data.addstory.repository.source.AddStoryDataSource
import com.ardnn.carita.data.addstory.repository.source.remote.request.AddStoryRequest
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.data.util.Source
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AddStoryRepositoryImpl @Inject constructor(
    private val addStoryDataFactory: AddStoryDataFactory
) : AddStoryRepository{

    private val remoteAddStoryDataSource: AddStoryDataSource by lazy {
        addStoryDataFactory.createData(Source.REMOTE)
    }

    override fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Observable<AddStoryResponse> =
        remoteAddStoryDataSource.postStory(token, file, description)

}