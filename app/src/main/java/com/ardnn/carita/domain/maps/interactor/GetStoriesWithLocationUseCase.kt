package com.ardnn.carita.domain.maps.interactor

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.domain.maps.repository.MapsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetStoriesWithLocationUseCase @Inject constructor(
    private val mapsRepository: MapsRepository
) {

    fun execute(token: String): Observable<StoriesResponse> =
        mapsRepository.getStories(token)
}