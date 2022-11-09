package com.ardnn.carita.data.addstory.interactor

import com.ardnn.carita.data.addstory.repository.AddStoryRepository
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostStoryUseCase @Inject constructor(
    private val addStoryRepository: AddStoryRepository
) {

    fun execute(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Observable<AddStoryResponse> =
        addStoryRepository.postStory(token, file, description)
}