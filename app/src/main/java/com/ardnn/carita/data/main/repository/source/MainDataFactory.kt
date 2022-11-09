package com.ardnn.carita.data.main.repository.source

import com.ardnn.carita.data.main.repository.source.local.LocalMainDataSource
import com.ardnn.carita.data.main.repository.source.remote.RemoteMainDataSource
import com.ardnn.carita.data.util.DataFactory
import com.ardnn.carita.data.util.Source
import javax.inject.Inject

class MainDataFactory @Inject constructor(
    private val localMainDataSource: LocalMainDataSource,
    private val remoteMainDataSource: RemoteMainDataSource
) : DataFactory<MainDataSource> {

    override fun createData(source: String): MainDataSource =
        when (source) {
            Source.LOCAL -> localMainDataSource
            Source.REMOTE -> remoteMainDataSource
            else -> remoteMainDataSource
        }
}