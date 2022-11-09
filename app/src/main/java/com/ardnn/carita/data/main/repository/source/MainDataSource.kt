package com.ardnn.carita.data.main.repository.source

import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import io.reactivex.Observable

interface MainDataSource {

    fun getHasBeenLaunch(): Observable<Boolean>

    fun saveHasBeenLaunch(): Observable<Unit>

    fun getUser(): Observable<User>

    fun getStories(token: String): Observable<StoriesResponse>

    fun logout(): Observable<Unit>
}