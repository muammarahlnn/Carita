package com.ardnn.carita.data.main.repository

import androidx.paging.PagingData
import com.ardnn.carita.data.main.repository.source.MainDataFactory
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.data.util.Source
import com.ardnn.carita.domain.main.repository.MainRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataFactory: MainDataFactory
) : MainRepository {

    override fun getHasBeenLaunched(): Observable<Boolean> =
        createLocalMainDataSource().getHasBeenLaunch()

    override fun saveHasBeenLaunched(): Observable<Unit> =
        createLocalMainDataSource().saveHasBeenLaunch()

    override fun getUser(): Observable<User> =
        createLocalMainDataSource().getUser()

    override fun getStories(token: String): Observable<PagingData<StoryResponse>> =
        createRemoteMainDataSource().getStories(token)

    override fun logout(): Observable<Unit> =
        createLocalMainDataSource().logout()


    private fun createRemoteMainDataSource(): MainDataSource =
        mainDataFactory.createData(Source.REMOTE)

    private fun createLocalMainDataSource(): MainDataSource =
        mainDataFactory.createData(Source.LOCAL)
}