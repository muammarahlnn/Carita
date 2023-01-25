package com.ardnn.carita.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.R
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.domain.login.interactor.PostLogin
import com.ardnn.carita.domain.login.interactor.SaveUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val postLogin: PostLogin,
    private val saveUser: SaveUser,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.None)
    val uiState = _uiState.asStateFlow()

    fun postLogin(request: LoginRequest) {
        postLogin.execute(
            params = PostLogin.Params(request),
            onStart = {
                _uiState.update {
                    LoginUiState.Loading(true)
                }
            },
            onSuccess = { response ->
                response.loginResult?.let {
                    val user = User(
                        it.userId.orEmpty(),
                        it.name.orEmpty(),
                        it.token.orEmpty()
                    )
                    saveUser(user)
                }
            },
            onError = { throwable ->
                _uiState.update {
                    LoginUiState.OnErrorPostLogin(throwable.message.toString())
                }
            },
            onCompletion = {
                _uiState.update {
                    LoginUiState.Loading(false)
                }
            },
            coroutineScope = viewModelScope
        )
    }

    private fun saveUser(user: User) {
        saveUser.execute(
            params = SaveUser.Params(user),
            onSuccess = {
                _uiState.update {
                    LoginUiState.OnSuccessSaveUser
                }
            },
            onError = {
                _uiState.update {
                    LoginUiState.ErrorToast(R.string.error_system_busy)
                }
            },
            coroutineScope = viewModelScope
        )
    }
}