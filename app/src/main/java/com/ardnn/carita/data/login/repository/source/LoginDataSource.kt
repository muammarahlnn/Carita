package com.ardnn.carita.data.login.repository.source

import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.model.User
import io.reactivex.Observable

interface LoginDataSource {

    fun postLogin(request: LoginRequest): Observable<LoginResponse>

    fun saveUser(user: User): Observable<Unit>
}