package com.ardnn.carita.data.login.repository

import com.ardnn.carita.data.login.repository.source.LoginDataFactory
import com.ardnn.carita.data.login.repository.source.LoginDataSource
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.util.Source
import io.reactivex.Observable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataFactory: LoginDataFactory
) : LoginRepository {

    override fun postLogin(request: LoginRequest): Observable<LoginResponse> =
        createRemoteLoginDataSource().postLogin(request)

    private fun createRemoteLoginDataSource(): LoginDataSource =
        loginDataFactory.createData(Source.REMOTE)
}