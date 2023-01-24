package com.ardnn.carita.data.onboarding.repository.source.local

import com.ardnn.carita.data.main.repository.source.local.MainPreferences
import com.ardnn.carita.data.onboarding.repository.source.OnBoardingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file LocalOnBoardingDataSource, 22/01/2023 13.12 by Muammar Ahlan Abimanyu
 */
class LocalOnBoardingDataSource @Inject constructor(
    private val mainPreferences: MainPreferences
) : OnBoardingDataSource{

    override suspend fun saveHasBeenLaunched() {
        mainPreferences.saveHasBeenLaunched()
    }
}