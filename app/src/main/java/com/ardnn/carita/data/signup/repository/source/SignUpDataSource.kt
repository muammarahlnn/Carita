package com.ardnn.carita.data.signup.repository.source

import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface SignUpDataSource {

    fun postRegister(request: RegisterRequest): Flow<RegisterResponse>
}