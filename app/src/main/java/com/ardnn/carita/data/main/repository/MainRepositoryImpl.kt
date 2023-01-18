package com.ardnn.carita.data.main.repository

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.MainDataFactory
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.main.repository.MainRepository
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataFactory: MainDataFactory
) : MainRepository {

    private val remoteDataSource: MainDataSource by lazy {
        mainDataFactory.createData(Source.REMOTE)
    }

    private val localDataSource: MainDataSource by lazy {
        mainDataFactory.createData(Source.LOCAL)
    }

    override fun getHasBeenLaunched(): Observable<Boolean> =
        localDataSource.getHasBeenLaunch()

    override fun saveHasBeenLaunched(): Observable<Unit> =
        localDataSource.saveHasBeenLaunch()

    override fun getUser(): Observable<User> =
        localDataSource.getUser()

    override fun getStories(token: String): Flow<PagingData<StoryResponse>> =
        remoteDataSource.getStories(token)

    override fun logout(): Observable<Unit> =
        localDataSource.logout()
}