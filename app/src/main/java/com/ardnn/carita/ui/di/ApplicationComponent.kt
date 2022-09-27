package com.ardnn.carita.ui.di

import android.content.Context
import com.ardnn.carita.data.di.LocalModule
import com.ardnn.carita.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AppScope
@Singleton
@Component(modules = [
    ApplicationModule::class,
    ViewModelModule::class,
    LocalModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}