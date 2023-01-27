package com.ardnn.carita.ui.signup


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SignUpUiState, 26/01/2023 12.57 by Muammar Ahlan Abimanyu
 */
sealed class SignUpUiState {

    object None : SignUpUiState()

    class Loading(val isLoading: Boolean) : SignUpUiState()

    object OnSuccessRegisterUser : SignUpUiState()

    class OnErrorRegisterUser(val errorMessage: String) : SignUpUiState()
}