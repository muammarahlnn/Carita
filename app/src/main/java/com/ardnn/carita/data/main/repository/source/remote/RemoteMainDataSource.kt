package com.ardnn.carita.data.main.repository.source.remote

import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
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

    override fun getStories(token: String): Observable<StoriesResponse> =
        api.getStories(token)

    override fun logout(): Observable<Unit> {
        throw UnsupportedOperationException("No implementation on remote data")
    }
}