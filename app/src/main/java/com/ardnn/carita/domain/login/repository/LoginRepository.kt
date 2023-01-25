package com.ardnn.carita.domain.login.repository

import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun postLogin(request: LoginRequest): Flow<LoginResponse>

    suspend fun saveUser(user: User)
}