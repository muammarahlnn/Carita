package com.ardnn.carita.data.di

import android.content.Context
import com.ardnn.carita.data.main.repository.source.local.MainPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideMainPreference(context: Context): MainPreference =
        MainPreference(context)
}