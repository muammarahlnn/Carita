package com.ardnn.carita.data.maps.repository.source.remote

import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.data.maps.repository.source.MapsDataSource
import io.reactivex.Observable
import javax.inject.Inject

class RemoteMapsDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : MapsDataSource {

    override fun getStories(token: String): Observable<StoriesResponse> =
        api.getStories(
            token = token,
            page = 1,
            size = 10,
            location = 1
        ).toObservable()
}