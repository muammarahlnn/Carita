package com.ardnn.carita.data.main.repository.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Observable
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

    override fun getStories(token: String): Observable<PagingData<StoryResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                StoryPagingSource(api, token)
            }
        ).observable
    }

    override fun logout(): Observable<Unit> {
        throw UnsupportedOperationException("No implementation on remote data")
    }
}