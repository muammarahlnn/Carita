package com.ardnn.carita.data.main.repository.source.remote

import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface StoryDicodingApi {

    @POST(REGISTER)
    fun postRegister(
        @Body request: RegisterRequest
    ): Observable<RegisterResponse>

    @POST(LOGIN)
    fun postLogin(
        @Body request: LoginRequest
    ): Observable<LoginResponse>

    companion object {

        private const val REGISTER = "v1/register"
        private const val LOGIN = "v1/login"
    }
}