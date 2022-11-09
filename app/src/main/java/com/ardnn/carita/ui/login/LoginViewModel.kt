package com.ardnn.carita.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.data.login.interactor.PostLoginUseCase
import com.ardnn.carita.data.login.interactor.SaveUserUseCase
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.vo.Event
import com.ardnn.carita.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _message = MutableLiveData<Event<String>>()
    val message get() = _message

    private val _response = MutableLiveData<Status<LoginResponse>>()
    val response get() = _response
    fun postLogin(request: LoginRequest) {
        disposables.add(
            postLoginUseCase.execute(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _response.value = Status.Loading()
                }
                .subscribe(
                    { loginResponse ->
                        _response.value = Status.Success(loginResponse)
                    },
                    {
                        _response.value = Status.Error(it.message.toString())
                        _message.value = Event(it.message.toString())
                        Timber.e(it.message)
                    }
                )
        )
    }

    private val _isUserSuccessfullySaved = MutableLiveData<Boolean>()
    val isUserSuccessfullySaved get() = _isUserSuccessfullySaved
    fun saveUser(user: User) {
        disposables.add(
            saveUserUseCase.execute(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _isUserSuccessfullySaved.value = true
                    },
                    {
                        _isUserSuccessfullySaved.value = false
                        Timber.e(it.message)
                    },
                    {
                        Timber.d("SaveUserUseCase complete")
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}