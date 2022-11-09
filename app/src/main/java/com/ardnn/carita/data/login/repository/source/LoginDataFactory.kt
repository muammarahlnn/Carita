package com.ardnn.carita.data.login.repository.source

import com.ardnn.carita.data.login.repository.source.local.LocalLoginDataSource
import com.ardnn.carita.data.login.repository.source.remote.RemoteLoginDataSource
import com.ardnn.carita.data.util.DataFactory
import com.ardnn.carita.data.util.Source
import javax.inject.Inject

class LoginDataFactory @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val localLoginDataSource: LocalLoginDataSource
) : DataFactory<LoginDataSource> {

    override fun createData(source: String): LoginDataSource =
        when (source) {
            Source.LOCAL -> localLoginDataSource
            Source.REMOTE -> remoteLoginDataSource
            else -> remoteLoginDataSource
        }
}