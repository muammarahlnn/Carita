package com.ardnn.carita.data.signup.repository.source

import com.ardnn.carita.data.signup.repository.source.remote.RemoteSignUpDataSource
import com.ardnn.carita.data.util.DataFactory
import javax.inject.Inject

class SignUpDataFactory @Inject constructor(
    private val remoteSignUpDataSource: RemoteSignUpDataSource
) : DataFactory<SignUpDataSource> {

    override fun createData(source: String): SignUpDataSource =
        remoteSignUpDataSource
}