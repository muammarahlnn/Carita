package com.ardnn.carita.data.main.repository

import io.reactivex.Observable

interface MainRepository {

    fun getHasBeenLaunched(): Observable<Boolean>

    fun saveHasBeenLaunched(): Observable<Unit>
}