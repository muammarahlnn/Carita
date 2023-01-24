package com.ardnn.carita.data.onboarding.repository.source

import com.ardnn.carita.data.onboarding.repository.source.local.LocalOnBoardingDataSource
import com.ardnn.carita.data.util.DataFactory
import com.ardnn.carita.data.util.Source
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file OnBoardingDataFactory, 22/01/2023 13.13 by Muammar Ahlan Abimanyu
 */
class OnBoardingDataFactory @Inject constructor(
    private val localOnBoardingDataSource: LocalOnBoardingDataSource
) : DataFactory<OnBoardingDataSource>{

    override fun createData(source: String): OnBoardingDataSource =
        localOnBoardingDataSource
}