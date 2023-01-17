package com.ardnn.carita.data.maps.repository

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.data.maps.repository.source.MapsDataFactory
import com.ardnn.carita.data.maps.repository.source.MapsDataSource
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.maps.repository.MapsRepository
import io.reactivex.Observable
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor(
    private val mapsDataFactory: MapsDataFactory
) : MapsRepository {

    private val remoteMapsDataSource: MapsDataSource by lazy {
        mapsDataFactory.createData(Source.REMOTE)
    }

    override fun getStories(token: String): Observable<StoriesResponse> =
        remoteMapsDataSource.getStories(token)
}