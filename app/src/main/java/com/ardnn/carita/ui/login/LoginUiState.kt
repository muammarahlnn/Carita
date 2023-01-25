package com.ardnn.carita.ui.login

import androidx.annotation.StringRes


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file LoginUiState, 24/01/2023 17.01 by Muammar Ahlan Abimanyu
 */
sealed class LoginUiState {

    object None : LoginUiState()

    class Loading(val isLoading: Boolean) : LoginUiState()

    class ErrorToast(@StringRes val stringId: Int) : LoginUiState()

    class OnErrorPostLogin(val errorMessage: String) : LoginUiState()

    object OnSuccessSaveUser : LoginUiState()
}