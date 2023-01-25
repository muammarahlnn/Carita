package com.ardnn.carita.domain.login.interactor

import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SaveUser, 24/01/2023 16.55 by Muammar Ahlan Abimanyu
 */
class SaveUser @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<SaveUser.Params, Unit>(){

    override fun buildUseCase(params: Params): Flow<Unit> =
        flow {
            emit(loginRepository.saveUser(params.user))
        }

    class Params(
        internal val user: User
    )
}