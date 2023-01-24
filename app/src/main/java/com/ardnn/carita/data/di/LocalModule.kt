package com.ardnn.carita.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ardnn.carita.data.main.repository.source.local.MainPreference
import com.ardnn.carita.data.main.repository.source.local.MainPreferences
import com.ardnn.carita.data.main.repository.source.local.mainDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideMainPreference(context: Context): MainPreference =
        MainPreference(context)

    @Provides
    @Singleton
    fun provideMainPreferences(context: Context): MainPreferences =
        MainPreferences(context.mainDataStore)
}