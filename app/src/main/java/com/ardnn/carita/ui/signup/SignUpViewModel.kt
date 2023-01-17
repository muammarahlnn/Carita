package com.ardnn.carita.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.domain.signup.interactor.PostRegisterUseCase
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import com.ardnn.carita.vo.Event
import com.ardnn.carita.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val postRegisterUseCase: PostRegisterUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _message = MutableLiveData<Event<String>>()
    val message get() = _message

    private val _response = MutableLiveData<Status<RegisterResponse>>()
    val response get() = _response
    fun postRegister(request: RegisterRequest) {
        disposables.add(
            postRegisterUseCase.execute(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _response.value = Status.Loading()
                }
                .subscribe(
                    { registerResponse ->
                        _response.value = Status.Success(registerResponse)
                        _message.value = Event(registerResponse.message.toString())
                    },
                    {
                        _response.value = Status.Error(it.message.toString())
                        _message.value = Event(it.message.toString())
                        Timber.e(it.message)
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}