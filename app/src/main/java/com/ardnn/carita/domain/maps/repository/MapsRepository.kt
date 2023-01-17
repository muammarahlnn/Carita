package com.ardnn.carita.domain.maps.repository

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import io.reactivex.Observable

interface MapsRepository {

    fun getStories(token: String): Observable<StoriesResponse>
}