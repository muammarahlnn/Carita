package com.ardnn.carita.data.maps.repository.source

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import kotlinx.coroutines.flow.Flow

interface MapsDataSource {

    fun getStories(): Flow<StoriesResponse>
}