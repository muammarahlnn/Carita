package com.ardnn.carita.domain.main.interactor

import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GetHasBeenLaunched, 19/01/2023 16.52 by Muammar Ahlan Abimanyu
 */
class GetHasBeenLaunched @Inject constructor(
    private val mainRepository: MainRepository
) : BaseUseCase<NoParams, Boolean>() {

    override fun buildUseCase(params: NoParams): Flow<Boolean> =
        mainRepository.getHasBeenLaunched()
}