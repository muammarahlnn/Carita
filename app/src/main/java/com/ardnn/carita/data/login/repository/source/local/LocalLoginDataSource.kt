package com.ardnn.carita.data.login.repository.source.local

import com.ardnn.carita.data.login.repository.source.LoginDataSource
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.MainPreferences
import com.ardnn.carita.data.main.repository.source.local.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalLoginDataSource @Inject constructor(
    private val mainPreferences: MainPreferences
) : LoginDataSource {

    override fun postLogin(request: LoginRequest): Flow<LoginResponse> {
        throw UnsupportedOperationException("No implementation on local data")
    }

    override suspend fun saveUser(user: User) {
        mainPreferences.saveUser(user)
    }
}