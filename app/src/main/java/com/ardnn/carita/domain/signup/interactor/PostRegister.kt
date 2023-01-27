package com.ardnn.carita.domain.signup.interactor

import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.signup.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file PostRegister, 26/01/2023 12.52 by Muammar Ahlan Abimanyu
 */
class PostRegister @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseUseCase<PostRegister.Params, RegisterResponse>(){

    override fun buildUseCase(params: Params): Flow<RegisterResponse> =
        signUpRepository.postRegister(params.request)

    class Params(
        internal val request: RegisterRequest
    )
}