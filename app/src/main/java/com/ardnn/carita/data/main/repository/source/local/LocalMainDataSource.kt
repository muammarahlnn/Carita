package com.ardnn.carita.data.main.repository.source.local

import com.ardnn.carita.data.main.repository.source.MainDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalMainDataSource @Inject constructor(
    private val mainPreference: MainPreference
) : MainDataSource {

    override fun getHasBeenLaunch(): Observable<Boolean> =
        Observable.just(mainPreference.getHasBeenLaunched())

    override fun saveHasBeenLaunch(): Observable<Unit> =
        Observable.just(mainPreference.saveHasBeenLaunched())
}