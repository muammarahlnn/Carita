package com.ardnn.carita.ui.onboarding

import androidx.annotation.StringRes


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file OnBoardingUiState, 22/01/2023 13.25 by Muammar Ahlan Abimanyu
 */
sealed class OnBoardingUiState {

    object None : OnBoardingUiState()

    class ErrorToast(@StringRes val stringId: Int) : OnBoardingUiState()

    object OnSuccessSaveHasBeenLaunched : OnBoardingUiState()
}