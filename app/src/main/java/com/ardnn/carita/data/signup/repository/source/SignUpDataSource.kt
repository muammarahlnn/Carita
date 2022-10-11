package com.ardnn.carita.data.signup.repository.source

import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import io.reactivex.Observable

interface SignUpDataSource {

    fun postRegister(request: RegisterRequest): Observable<RegisterResponse>
}