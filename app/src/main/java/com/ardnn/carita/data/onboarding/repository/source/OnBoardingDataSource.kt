package com.ardnn.carita.data.onboarding.repository.source

import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file OnBoardingDataSource, 22/01/2023 13.11 by Muammar Ahlan Abimanyu
 */
interface OnBoardingDataSource {

    suspend fun saveHasBeenLaunched()
}