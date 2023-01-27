package com.ardnn.carita.ui.addstory


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AddStoryUiState, 27/01/2023 21.10 by Muammar Ahlan Abimanyu
 */
sealed class AddStoryUiState {

    object None : AddStoryUiState()

    class Loading(val isLoading: Boolean) : AddStoryUiState()

    object OnSuccessAddStory : AddStoryUiState()

    class OnErrorPostStory(val errorMessage: String) : AddStoryUiState()
}