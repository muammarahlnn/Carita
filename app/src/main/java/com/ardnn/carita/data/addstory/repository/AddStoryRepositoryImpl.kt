package com.ardnn.carita.data.addstory.repository

import com.ardnn.carita.data.addstory.repository.source.AddStoryDataFactory
import com.ardnn.carita.data.addstory.repository.source.AddStoryDataSource
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.addstory.repository.AddStoryRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AddStoryRepositoryImpl @Inject constructor(
    private val addStoryDataFactory: AddStoryDataFactory
) : AddStoryRepository {

    private val remoteAddStoryDataSource: AddStoryDataSource by lazy {
        addStoryDataFactory.createData(Source.REMOTE)
    }

    override fun postStory(
        file: MultipartBody.Part,
        description: RequestBody
    ): Flow<AddStoryResponse> = remoteAddStoryDataSource.postStory(file, description)
}