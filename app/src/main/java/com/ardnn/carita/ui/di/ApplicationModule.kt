package com.ardnn.carita.ui.di

import com.ardnn.carita.data.addstory.repository.AddStoryRepositoryImpl
import com.ardnn.carita.data.login.repository.LoginRepositoryImpl
import com.ardnn.carita.data.main.repository.MainRepositoryImpl
import com.ardnn.carita.data.maps.repository.MapsRepositoryImpl
import com.ardnn.carita.data.onboarding.repository.OnBoardingRepositoryImpl
import com.ardnn.carita.data.signup.repository.SignUpRepositoryImpl
import com.ardnn.carita.domain.addstory.repository.AddStoryRepository
import com.ardnn.carita.domain.login.repository.LoginRepository
import com.ardnn.carita.domain.main.repository.MainRepository
import com.ardnn.carita.domain.maps.repository.MapsRepository
import com.ardnn.carita.domain.onboarding.repository.OnBoardingRepository
import com.ardnn.carita.domain.signup.repository.SignUpRepository
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
    abstract fun provideOnBoardingRepository(onBoardingRepository: OnBoardingRepositoryImpl): OnBoardingRepository

    @Binds
    @Singleton
    abstract fun provideSignUpRepository(signUpRepository: SignUpRepositoryImpl): SignUpRepository

    @Binds
    @Singleton
    abstract fun provideLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun provideAddStoryRepository(addStoryRepository: AddStoryRepositoryImpl): AddStoryRepository

    @Binds
    @Singleton
    abstract fun provideMapsRepository(mapsRepository: MapsRepositoryImpl): MapsRepository
}