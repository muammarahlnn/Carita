package com.ardnn.carita.ui.addstory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.domain.addstory.interactor.PostStory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AddStoryViewModel @Inject constructor(
    private val postStory: PostStory
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddStoryUiState>(AddStoryUiState.None)
    val uiState = _uiState.asStateFlow()

    fun postStory(
        file: MultipartBody.Part,
        description: RequestBody
    ) {
        postStory.execute(
            params = PostStory.Params(file, description),
            onStart = {
                _uiState.update {
                    AddStoryUiState.Loading(true)
                }
            },
            onSuccess = {
                _uiState.update {
                    AddStoryUiState.OnSuccessAddStory
                }
            },
            onError = { throwable ->
                _uiState.update {
                    AddStoryUiState.OnErrorPostStory(throwable.message.toString())
                }
            },
            onCompletion = {
                _uiState.update {
                    AddStoryUiState.Loading(false)
                }
            },
            coroutineScope = viewModelScope
        )
    }
}