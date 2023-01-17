package com.ardnn.carita.data.login.repository

import com.ardnn.carita.data.login.repository.source.LoginDataFactory
import com.ardnn.carita.data.login.repository.source.LoginDataSource
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.login.repository.LoginRepository
import io.reactivex.Observable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataFactory: LoginDataFactory
) : LoginRepository {

    private val localLoginDataSource: LoginDataSource by lazy {
        loginDataFactory.createData(Source.LOCAL)
    }

    private val remoteLoginDataSource: LoginDataSource by lazy {
        loginDataFactory.createData(Source.REMOTE)
    }

    override fun postLogin(request: LoginRequest): Observable<LoginResponse> =
        remoteLoginDataSource.postLogin(request)

    override fun saveUser(user: User): Observable<Unit> =
        localLoginDataSource.saveUser(user)
}