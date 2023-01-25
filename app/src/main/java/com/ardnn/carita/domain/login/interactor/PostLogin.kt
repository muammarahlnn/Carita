package com.ardnn.carita.domain.login.interactor

import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file PostLogin, 24/01/2023 16.57 by Muammar Ahlan Abimanyu
 */
class PostLogin @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<PostLogin.Params, LoginResponse>(){

    override fun buildUseCase(params: Params): Flow<LoginResponse> =
        loginRepository.postLogin(params.loginRequest)

    class Params(
        internal val loginRequest: LoginRequest
    )
}