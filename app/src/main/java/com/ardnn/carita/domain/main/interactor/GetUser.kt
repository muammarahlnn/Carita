package com.ardnn.carita.domain.main.interactor

import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GetUser, 19/01/2023 17.55 by Muammar Ahlan Abimanyu
 */
class GetUser @Inject constructor(
    private val mainRepository: MainRepository
) : BaseUseCase<NoParams, User>() {

    override fun buildUseCase(params: NoParams): Flow<User> =
        mainRepository.getUser()
}