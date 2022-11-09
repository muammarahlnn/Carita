package com.ardnn.carita.data.login.repository.source.local

import com.ardnn.carita.data.login.repository.source.LoginDataSource
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.MainPreference
import com.ardnn.carita.data.main.repository.source.local.model.User
import io.reactivex.Observable
import javax.inject.Inject

class LocalLoginDataSource @Inject constructor(
    private val mainPreference: MainPreference
) : LoginDataSource {

    override fun postLogin(request: LoginRequest): Observable<LoginResponse> {
        throw UnsupportedOperationException("No implementation on local data")
    }

    override fun saveUser(user: User): Observable<Unit> =
        Observable.just(mainPreference.saveUser(user))

}