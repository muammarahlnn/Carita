package com.ardnn.carita.data.main.repository.source

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import kotlinx.coroutines.flow.Flow

interface MainDataSource {

    fun getHasBeenLaunch(): Flow<Boolean>

    fun getUser(): Flow<User>

    fun getStories(): Flow<PagingData<StoryResponse>>

    suspend fun logout()
}