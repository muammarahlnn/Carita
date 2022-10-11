package com.ardnn.carita.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.data.signup.interactor.PostRegisterUseCase
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.vo.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val postRegisterUseCase: PostRegisterUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _snackbarMessage = MutableLiveData<Event<String>>()
    val snackbarMessage: LiveData<Event<String>> = _snackbarMessage
    fun postRegister(request: RegisterRequest) {
        disposables.add(
            postRegisterUseCase.execute(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { registerResponse ->
                        _snackbarMessage.value = Event(registerResponse.message.toString())
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