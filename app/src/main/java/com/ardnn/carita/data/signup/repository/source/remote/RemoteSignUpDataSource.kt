package com.ardnn.carita.data.signup.repository.source.remote

import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import com.ardnn.carita.data.signup.repository.source.SignUpDataSource
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteSignUpDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : SignUpDataSource {

    override fun postRegister(request: RegisterRequest): Flow<RegisterResponse> =
        flow {
            emit(api.postRegister(request))
        }
}