package com.ardnn.carita.data.maps.repository.source

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import io.reactivex.Observable

interface MapsDataSource {

    fun getStories(token: String): Observable<StoriesResponse>
}