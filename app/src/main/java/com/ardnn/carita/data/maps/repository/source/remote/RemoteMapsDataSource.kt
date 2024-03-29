package com.ardnn.carita.data.maps.repository.source.remote

import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.data.maps.repository.source.MapsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteMapsDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : MapsDataSource {

    override fun getStories(): Flow<StoriesResponse> {
        return flow {
            emit(
                api.getStories(
                    page = 1,
                    size = 30,
                    location = 1
                )
            )
        }
    }
}