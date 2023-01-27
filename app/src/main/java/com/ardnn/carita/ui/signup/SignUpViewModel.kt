package com.ardnn.carita.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.domain.signup.interactor.PostRegister
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val postRegister: PostRegister
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.None)
    val uiState = _uiState.asStateFlow()

    fun postRegister(request: RegisterRequest) {
        postRegister.execute(
            params = PostRegister.Params(request),
            onStart = {
                _uiState.update {
                    SignUpUiState.Loading(true)
                }
            },
            onSuccess = {
                _uiState.update {
                    SignUpUiState.OnSuccessRegisterUser
                }
            },
            onError = { throwable ->
                _uiState.update {
                    SignUpUiState.OnErrorRegisterUser(throwable.message.toString())
                }
            },
            onCompletion = {
                _uiState.update {
                    SignUpUiState.Loading(false)
                }
            },
            coroutineScope = viewModelScope
        )
    }
}