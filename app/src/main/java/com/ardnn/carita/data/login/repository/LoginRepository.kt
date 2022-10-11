package com.ardnn.carita.data.login.repository

import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import io.reactivex.Observable

interface LoginRepository {

    fun postLogin(request: LoginRequest): Observable<LoginResponse>
}