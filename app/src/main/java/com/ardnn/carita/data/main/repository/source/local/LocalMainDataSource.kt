package com.ardnn.carita.data.main.repository.source.local

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMainDataSource @Inject constructor(
    private val mainPreferences: MainPreferences
) : MainDataSource {

    override fun getHasBeenLaunch(): Flow<Boolean> =
        mainPreferences.getHasBeenLaunched()

    override fun getUser(): Flow<User> =
        mainPreferences.getUser()

    override fun getStories(token: String): Flow<PagingData<StoryResponse>> {
        throw UnsupportedOperationException("No implementation on local data")
    }

    override suspend fun logout() {
        mainPreferences.logout()
    }
}