package com.ardnn.carita.domain.main.repository

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getHasBeenLaunched(): Observable<Boolean>

    fun saveHasBeenLaunched(): Observable<Unit>

    fun getUser(): Observable<User>

    fun getStories(token: String): Flow<PagingData<StoryResponse>>

    fun logout(): Observable<Unit>
}