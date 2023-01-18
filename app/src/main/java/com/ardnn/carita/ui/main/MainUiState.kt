package com.ardnn.carita.ui.main

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MainUiState, 18/01/2023 16.15 by Muammar Ahlan Abimanyu
 */
sealed class MainUiState {
    
    object None : MainUiState()

    class Loading(val isLoading: Boolean) : MainUiState()

    class Error(@StringRes val stringId: Int) : MainUiState()

    class OnSuccessGetStories(val stories: PagingData<StoryResponse>) : MainUiState()
}