package com.ardnn.carita.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.R
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.main.interactor.*
import com.ardnn.carita.domain.onboarding.interactor.SaveHasBeenLaunched
import com.ardnn.carita.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getHasBeenLaunchedUseCase: GetHasBeenLaunchedUseCase,
    private val getHasBeenLaunched: GetHasBeenLaunched,
    private val saveHasBeenLaunchedUseCase: SaveHasBeenLaunchedUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getUser: GetUser,
    private val logoutUseCase: LogoutUseCase,
    private val logout: Logout,
    private val getStories: GetStories
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.None)
    val uiState = _uiState.asStateFlow()

    fun getHasBeenLaunchedFlow() {
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

    fun getUserFlow() {
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

    fun logoutFlow() {
//        logout.execute(
//            params = NoParams,
//            onStart = {
//                _uiState.update {
//                    MainUiState.Loading(true)
//                }
//            },
//            onSuccess = {
//                _uiState.update {
//                    MainUiState.OnSuccessLogout
//                }
//            },
//            onError = {
//                Timber.e(it.message)
//                _uiState.update {
//                    MainUiState.ErrorToast(R.string.error_logout)
//                }
//            },
//            onCompletion = {
//                _uiState.update {
//                    MainUiState.Loading(false)
//                }
//            },
//            coroutineScope = viewModelScope
//        )
    }

    fun getStories(token: String) {
        getStories.execute(
            params = GetStories.Params(
                "Bearer $token",
                viewModelScope
            ),
            onStart = {
                _uiState.update {
                    MainUiState.Loading(true)
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
                  MainUiState.Loading(false)
              }
            },
            coroutineScope = viewModelScope
        )
    }

    private val disposables = CompositeDisposable()

    private val _hasBeenLaunched = MutableLiveData<Boolean>()
    val hasBeenLaunched: LiveData<Boolean> get() = _hasBeenLaunched
    fun getHasBeenLaunched() {
        disposables.add(
            getHasBeenLaunchedUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { hasBeenLaunched ->
                        _hasBeenLaunched.postValue(hasBeenLaunched)
                    },
                    {
                        Timber.d(it.message)
                    }
                )
        )
    }

    fun saveHasBeenLaunched() {
        disposables.add(
            saveHasBeenLaunchedUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        // no implementation
                    },
                    {
                        Timber.d(it.message)
                    },
                    {
                        Timber.d("SaveHasBeenLaunchedUseCase complete")
                    }
                )
        )
    }

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user
    fun getUser() {
        disposables.add(
            getUserUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { user ->
                        _user.value = user
                        _isLogin.value = !(user.name.isEmpty()
                                && user.userId.isEmpty()
                                && user.token.isEmpty())
                    },
                    {
                        Timber.e(it.message.toString())
                    }
                )
        )
    }

    private val _logoutStatus = MutableLiveData<Status<String>>()
    val logoutStatus: LiveData<Status<String>> get() = _logoutStatus
    fun logout() {
        disposables.add(
            logoutUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _logoutStatus.value = Status.Loading()
                }
                .subscribe(
                    {
                        _logoutStatus.value = Status.Success("Logout success")
                    },
                    {
                        _logoutStatus.value = Status.Error("An error occurred")
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}