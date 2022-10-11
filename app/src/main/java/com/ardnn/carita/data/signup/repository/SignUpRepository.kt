package com.ardnn.carita.data.signup.repository

import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import io.reactivex.Observable

interface SignUpRepository {

    fun postRegister(request: RegisterRequest): Observable<RegisterResponse>
}