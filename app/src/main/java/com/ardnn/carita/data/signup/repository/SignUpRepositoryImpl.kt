package com.ardnn.carita.data.signup.repository

import com.ardnn.carita.data.signup.repository.source.SignUpDataFactory
import com.ardnn.carita.data.signup.repository.source.SignUpDataSource
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import com.ardnn.carita.data.util.Source
import io.reactivex.Observable
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataFactory: SignUpDataFactory
) : SignUpRepository {

    override fun postRegister(request: RegisterRequest): Observable<RegisterResponse> =
        createRemoteSignUpDataSource().postRegister(request)

    private fun createRemoteSignUpDataSource(): SignUpDataSource =
        signUpDataFactory.createData(Source.REMOTE)
}