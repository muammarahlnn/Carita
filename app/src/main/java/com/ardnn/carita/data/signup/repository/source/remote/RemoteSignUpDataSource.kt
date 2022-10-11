package com.ardnn.carita.data.signup.repository.source.remote

import com.ardnn.carita.data.main.repository.source.remote.StoryDicodingApi
import com.ardnn.carita.data.signup.repository.source.SignUpDataSource
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import io.reactivex.Observable
import javax.inject.Inject

class RemoteSignUpDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : SignUpDataSource {

    override fun postRegister(request: RegisterRequest): Observable<RegisterResponse> =
        api.postRegister(request)
}