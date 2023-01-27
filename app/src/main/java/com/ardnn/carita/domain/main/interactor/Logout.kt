package com.ardnn.carita.domain.main.interactor

import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Logout, 19/01/2023 16.54 by Muammar Ahlan Abimanyu
 */
class Logout @Inject constructor(
    private val mainRepository: MainRepository
) : BaseUseCase<NoParams, Unit>() {

    override fun buildUseCase(params: NoParams): Flow<Unit> =
        flow {
            emit(mainRepository.logout())
        }
}