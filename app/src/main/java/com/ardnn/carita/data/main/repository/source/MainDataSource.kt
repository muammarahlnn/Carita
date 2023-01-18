package com.ardnn.carita.data.main.repository.source

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface MainDataSource {

    fun getHasBeenLaunch(): Observable<Boolean>

    fun saveHasBeenLaunch(): Observable<Unit>

    fun getUser(): Observable<User>

    fun getStories(token: String): Flow<PagingData<StoryResponse>>

    fun logout(): Observable<Unit>
}