package com.ardnn.carita.ui.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.maps.interactor.GetStoriesWithLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val getStoriesWithLocation: GetStoriesWithLocation
) : ViewModel() {

    private val _uiState = MutableStateFlow<MapsUiState>(MapsUiState.None)
    val uiState = _uiState.asStateFlow()

    fun getStories() {
        getStoriesWithLocation.execute(
            params = NoParams,
            onSuccess = { response ->
                response.listStory?.let { stories ->
                    _uiState.update {
                        MapsUiState.OnSuccessGetStories(stories)
                    }
                }
            },
            onError = { throwable ->
                _uiState.update {
                    MapsUiState.OnErrorGetStories(throwable.message.toString())
                }
            },
            coroutineScope = viewModelScope
        )
    }
}