package com.ardnn.carita.data.main.interactor

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.MainRepository
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Observable
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun execute(token: String): Observable<PagingData<StoryResponse>> =
        mainRepository.getStories(token)
}