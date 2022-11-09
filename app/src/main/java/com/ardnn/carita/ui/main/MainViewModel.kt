package com.ardnn.carita.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.data.main.interactor.*
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getHasBeenLaunchedUseCase: GetHasBeenLaunchedUseCase,
    private val saveHasBeenLaunchedUseCase: SaveHasBeenLaunchedUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getStoriesUseCase: GetStoriesUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

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
                        _isLogin.value = !(user.name.isNullOrEmpty()
                                && user.userId.isNullOrEmpty()
                                && user.token.isNullOrEmpty())
                    },
                    {
                        Timber.e(it.message.toString())
                    }
                )
        )
    }

    private val _stories = MutableLiveData<Status<List<StoryResponse>>>()
    val stories: LiveData<Status<List<StoryResponse>>> get() = _stories
    fun getStories(token: String) {
        disposables.add(
            getStoriesUseCase.execute("Bearer $token")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _stories.value = Status.Loading()
                }
                .subscribe(
                    { storiesResponse ->
                        _stories.value = Status.Success(
                            storiesResponse.listStory ?: listOf()
                        )
                    },
                    {
                        _stories.value = Status.Error(it.message.toString())
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