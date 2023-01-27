package com.ardnn.carita.domain.maps.repository

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import kotlinx.coroutines.flow.Flow

interface MapsRepository {

    fun getStories(token: String): Flow<StoriesResponse>
}