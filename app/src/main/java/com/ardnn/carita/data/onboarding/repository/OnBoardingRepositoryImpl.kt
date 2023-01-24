package com.ardnn.carita.data.onboarding.repository

import com.ardnn.carita.data.onboarding.repository.source.OnBoardingDataFactory
import com.ardnn.carita.data.onboarding.repository.source.OnBoardingDataSource
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.onboarding.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file OnBoardingRepositoryImpl, 22/01/2023 13.11 by Muammar Ahlan Abimanyu
 */
class OnBoardingRepositoryImpl @Inject constructor(
    private val onBoardingDataFactory: OnBoardingDataFactory
) : OnBoardingRepository {

    private val localDataSource: OnBoardingDataSource by lazy {
        onBoardingDataFactory.createData(Source.LOCAL)
    }

    override suspend fun saveHasBeenLaunched() {
        localDataSource.saveHasBeenLaunched()
    }
}