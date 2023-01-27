package com.ardnn.carita.domain.signup.repository

import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun postRegister(request: RegisterRequest): Flow<RegisterResponse>
}