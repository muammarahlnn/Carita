package com.ardnn.carita.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.data.login.interactor.PostLoginUseCase
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.vo.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _snackbarMessage = MutableLiveData<Event<String>>()
    val snackbarMessage get() = _snackbarMessage
    fun postLogin(request: LoginRequest) {
        disposables.add(
            postLoginUseCase.execute(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { loginResponse ->
                        _snackbarMessage.value = Event(loginResponse.message.toString())
                    },
                    {
                        _snackbarMessage.value = Event(it.message.toString())
                        Timber.e(it.message)
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}