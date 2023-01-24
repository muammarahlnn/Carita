package com.ardnn.carita.domain.onboarding.repository

import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file OnBoardingRepository, 22/01/2023 13.05 by Muammar Ahlan Abimanyu
 */
interface OnBoardingRepository {

    suspend fun saveHasBeenLaunched()
}