package com.ardnn.carita.data.main.repository.source.local

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMainDataSource @Inject constructor(
    private val mainPreference: MainPreference
) : MainDataSource {

    override fun getHasBeenLaunch(): Observable<Boolean> =
        Observable.just(mainPreference.getHasBeenLaunched())

    override fun saveHasBeenLaunch(): Observable<Unit> =
        Observable.just(mainPreference.saveHasBeenLaunched())

    override fun getUser(): Observable<User> =
        Observable.just(mainPreference.getUser())

    override fun getStories(token: String): Flow<PagingData<StoryResponse>> {
        throw UnsupportedOperationException("No implementation on local data")
    }

    override fun logout(): Observable<Unit> =
        Observable.just(mainPreference.logout())

}