package com.ardnn.carita.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.R
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.main.interactor.GetHasBeenLaunched
import com.ardnn.carita.domain.main.interactor.GetStories
import com.ardnn.carita.domain.main.interactor.GetUser
import com.ardnn.carita.domain.main.interactor.Logout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getHasBeenLaunched: GetHasBeenLaunched,
    private val getUser: GetUser,
    private val logout: Logout,
    private val getStories: GetStories
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.None)
    val uiState = _uiState.asStateFlow()

    fun getHasBeenLaunched() {
        getHasBeenLaunched.execute(
            params = NoParams,
            onSuccess = { hasBeenLaunched ->
                _uiState.update {
                    MainUiState.OnSuccessGetHasBeenLaunched(hasBeenLaunched)
                }
            },
            onError = {
                Timber.e(it.message)
                _uiState.update {
                    MainUiState.Error(R.string.error_connection)
                }
            },
            coroutineScope = viewModelScope
        )
    }

    fun getUser() {
        getUser.execute(
            params = NoParams,
            onSuccess = { user ->
                val isLogin = user.name.isNotEmpty()
                        && user.userId.isNotEmpty()
                        && user.token.isNotEmpty()

                if (isLogin) {
                    _uiState.update {
                        MainUiState.OnUserIsLogin(user)
                    }
                } else {
                    _uiState.update {
                        MainUiState.OnUserNotLogin
                    }
                }
            },
            onError = {
                Timber.e(it.message)
            },
            coroutineScope = viewModelScope
        )
    }

    fun logout() {
        logout.execute(
            params = NoParams,
            onStart = {
                _uiState.update {
                    MainUiState.LoadingProgressBar(true)
                }
            },
            onSuccess = {
                _uiState.update {
                    MainUiState.OnSuccessLogout
                }
            },
            onError = {
                Timber.e(it.message)
                _uiState.update {
                    MainUiState.ErrorToast(R.string.error_logout)
                }
            },
            onCompletion = {
                _uiState.update {
                    MainUiState.LoadingProgressBar(false)
                }
            },
            coroutineScope = viewModelScope
        )
    }

    fun getStories() {
        getStories.execute(
            params = GetStories.Params(viewModelScope),
            onStart = {
                _uiState.update {
                    MainUiState.LoadingShimmer(true)
                }
            },
            onSuccess = { stories ->
                _uiState.update {
                    MainUiState.OnSuccessGetStories(stories)
                }
            },
            onError = {
                Timber.e(it.message)
                _uiState.update {
                    MainUiState.Error(R.string.error_connection)
                }
            },
            onCompletion = {
              _uiState.update {
                  MainUiState.LoadingShimmer(false)
              }
            },
            coroutineScope = viewModelScope
        )
    }
}