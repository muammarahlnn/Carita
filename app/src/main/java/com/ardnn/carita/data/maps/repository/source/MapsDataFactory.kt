package com.ardnn.carita.data.maps.repository.source

import com.ardnn.carita.data.maps.repository.source.remote.RemoteMapsDataSource
import com.ardnn.carita.data.util.DataFactory
import javax.inject.Inject

class MapsDataFactory @Inject constructor(
    private val remoteMapsDataSource: RemoteMapsDataSource
) : DataFactory<MapsDataSource> {

    override fun createData(source: String): MapsDataSource =
        remoteMapsDataSource
}