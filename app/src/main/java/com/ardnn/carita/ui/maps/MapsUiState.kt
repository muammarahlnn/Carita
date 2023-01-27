package com.ardnn.carita.ui.maps

import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MapsUiState, 27/01/2023 21.54 by Muammar Ahlan Abimanyu
 */
sealed class MapsUiState {

    object None : MapsUiState()

    class OnSuccessGetStories(val stories: List<StoryResponse>) : MapsUiState()

    class OnErrorGetStories(val errorMessage: String) : MapsUiState()
}