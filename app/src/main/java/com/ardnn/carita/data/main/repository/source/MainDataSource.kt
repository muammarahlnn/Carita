package com.ardnn.carita.data.main.repository.source

import io.reactivex.Observable

interface MainDataSource {

    fun getHasBeenLaunch(): Observable<Boolean>

    fun saveHasBeenLaunch(): Observable<Unit>
}