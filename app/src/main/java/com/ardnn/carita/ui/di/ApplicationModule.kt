package com.ardnn.carita.ui.di

import com.ardnn.carita.data.addstory.repository.AddStoryRepository
import com.ardnn.carita.data.addstory.repository.AddStoryRepositoryImpl
import com.ardnn.carita.data.login.repository.LoginRepository
import com.ardnn.carita.data.login.repository.LoginRepositoryImpl
import com.ardnn.carita.data.main.repository.MainRepository
import com.ardnn.carita.data.main.repository.MainRepositoryImpl
import com.ardnn.carita.data.signup.repository.SignUpRepository
import com.ardnn.carita.data.signup.repository.SignUpRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun provideMainRepository(mainRepository: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    abstract fun provideSignUpRepository(signUpRepository: SignUpRepositoryImpl): SignUpRepository

    @Binds
    @Singleton
    abstract fun provideLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun provideAddStoryRepository(addStoryRepository: AddStoryRepositoryImpl): AddStoryRepository
}