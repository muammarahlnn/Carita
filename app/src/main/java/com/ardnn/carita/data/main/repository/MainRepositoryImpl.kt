package com.ardnn.carita.data.main.repository

import com.ardnn.carita.data.main.repository.source.MainDataFactory
import com.ardnn.carita.data.main.repository.source.MainDataSource
import com.ardnn.carita.data.util.Source
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataFactory: MainDataFactory
) : MainRepository {

    override fun getHasBeenLaunched(): Observable<Boolean> =
        createLocalMainDataSource().getHasBeenLaunch()

    override fun saveHasBeenLaunched(): Observable<Unit> =
        createLocalMainDataSource().saveHasBeenLaunch()

    private fun createLocalMainDataSource(): MainDataSource =
        mainDataFactory.createData(Source.LOCAL)
}