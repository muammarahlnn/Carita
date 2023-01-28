package com.ardnn.carita.data.main.repository.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteMainDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : MainDataSource {

    override fun getHasBeenLaunch(): Flow<Boolean> {
        throw UnsupportedOperationException("No implementation on remote data")
    }

    override fun getUser(): Flow<User> {
        throw UnsupportedOperationException("No implementation on remote data")
    }

    override fun getStories(): Flow<PagingData<StoryResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                StoryPagingSource(api)
            }
        ).flow
    }

    override suspend fun logout() {
        throw UnsupportedOperationException("No implementation on remote data")
    }
}