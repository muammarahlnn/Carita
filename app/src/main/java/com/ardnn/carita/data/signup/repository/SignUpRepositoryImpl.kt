package com.ardnn.carita.data.signup.repository

import com.ardnn.carita.data.signup.repository.source.SignUpDataFactory
import com.ardnn.carita.data.signup.repository.source.SignUpDataSource
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.signup.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataFactory: SignUpDataFactory
) : SignUpRepository {

    private val remoteDataSource: SignUpDataSource by lazy {
        signUpDataFactory.createData(Source.REMOTE)
    }

    override fun postRegister(request: RegisterRequest): Flow<RegisterResponse> =
        remoteDataSource.postRegister(request)
}