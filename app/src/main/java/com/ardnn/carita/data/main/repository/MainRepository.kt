package com.ardnn.carita.data.main.repository

import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import io.reactivex.Observable

interface MainRepository {

    fun getHasBeenLaunched(): Observable<Boolean>

    fun saveHasBeenLaunched(): Observable<Unit>

    fun getUser(): Observable<User>

    fun getStories(token: String): Observable<StoriesResponse>

    fun logout(): Observable<Unit>
}