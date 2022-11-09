package com.ardnn.carita.data.login.repository.source.remote

import com.ardnn.carita.data.login.repository.source.LoginDataSource
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import io.reactivex.Observable
import javax.inject.Inject

class RemoteLoginDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : LoginDataSource {

    override fun postLogin(request: LoginRequest): Observable<LoginResponse> =
        api.postLogin(request)

    override fun saveUser(user: User): Observable<Unit> {
        throw UnsupportedOperationException("No implementation on remote data")
    }
}