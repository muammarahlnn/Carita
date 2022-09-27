package com.ardnn.carita.data.main.repository.source

import com.ardnn.carita.data.main.repository.source.local.LocalMainDataSource
import com.ardnn.carita.data.util.DataFactory
import javax.inject.Inject

class MainDataFactory @Inject constructor(
    private val localMainDataSource: LocalMainDataSource
) : DataFactory<MainDataSource> {

    override fun createData(source: String): MainDataSource =
        localMainDataSource
}