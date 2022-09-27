package com.ardnn.carita.ui.di

import com.ardnn.carita.data.main.repository.MainRepository
import com.ardnn.carita.data.main.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun provideMainRepository(mainRepository: MainRepositoryImpl): MainRepository
}