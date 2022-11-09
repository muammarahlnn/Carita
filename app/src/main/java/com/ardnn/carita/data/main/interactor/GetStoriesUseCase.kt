package com.ardnn.carita.data.main.interactor

import com.ardnn.carita.data.main.repository.MainRepository
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import io.reactivex.Observable
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun execute(token: String): Observable<StoriesResponse> =
        mainRepository.getStories(token)
}