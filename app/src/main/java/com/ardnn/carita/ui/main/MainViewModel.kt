package com.ardnn.carita.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.data.main.interactor.GetHasBeenLaunchedUseCase
import com.ardnn.carita.data.main.interactor.SaveHasBeenLaunchedUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getHasBeenLaunchedUseCase: GetHasBeenLaunchedUseCase,
    private val saveHasBeenLaunchedUseCase: SaveHasBeenLaunchedUseCase,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _hasBeenLaunched = MutableLiveData(false)
    val hasBeenLaunched get() = _hasBeenLaunched
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

    override fun onCleared() {
        disposables.clear()
    }
}