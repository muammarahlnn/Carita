package com.ardnn.carita.data.main.repository.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteMainDataSource @Inject constructor(
    private val api: StoryDicodingApi
) : MainDataSource {

    override fun getHasBeenLaunch(): Observable<Boolean> {
        throw UnsupportedOperationException("No implementation on remote data")
    }

    override fun saveHasBeenLaunch(): Observable<Unit> {
        throw UnsupportedOperationException("No implementation on remote data")
    }

    override fun getUser(): Observable<User> {
        throw UnsupportedOperationException("No implementation on remote data")
    }

    override fun getStories(token: String): Flow<PagingData<StoryResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                StoryPagingSource(api, token)
            }
        ).flow
    }

    override fun logout(): Observable<Unit> {
        throw UnsupportedOperationException("No implementation on remote data")
    }
}