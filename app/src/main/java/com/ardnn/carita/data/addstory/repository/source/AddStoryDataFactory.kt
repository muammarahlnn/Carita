package com.ardnn.carita.data.addstory.repository.source

import com.ardnn.carita.data.addstory.repository.source.remote.RemoteAddStoryDataSource
import com.ardnn.carita.data.util.DataFactory
import javax.inject.Inject

class AddStoryDataFactory @Inject constructor(
    private val remoteAddStoryDataSource: RemoteAddStoryDataSource
) : DataFactory<AddStoryDataSource> {

    override fun createData(source: String): AddStoryDataSource =
        remoteAddStoryDataSource
}