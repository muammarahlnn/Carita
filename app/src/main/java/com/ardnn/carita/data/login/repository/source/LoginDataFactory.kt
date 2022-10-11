package com.ardnn.carita.data.login.repository.source

import com.ardnn.carita.data.login.repository.source.remote.RemoteLoginDataSource
import com.ardnn.carita.data.util.DataFactory
import javax.inject.Inject

class LoginDataFactory @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
) : DataFactory<LoginDataSource> {

    override fun createData(source: String): LoginDataSource =
        remoteLoginDataSource
}