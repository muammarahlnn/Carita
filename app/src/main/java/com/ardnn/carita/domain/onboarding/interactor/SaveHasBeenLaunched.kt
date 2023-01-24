package com.ardnn.carita.domain.onboarding.interactor

import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.onboarding.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SaveHasBeenLaunched, 19/01/2023 17.54 by Muammar Ahlan Abimanyu
 */
class SaveHasBeenLaunched @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
) : BaseUseCase<NoParams, Unit>() {

    override fun buildUseCase(params: NoParams): Flow<Unit> {
        return flow {
            emit(onBoardingRepository.saveHasBeenLaunched())
        }
    }
}