package com.ardnn.carita.ui.di

import android.content.Context
import com.ardnn.carita.data.di.LocalModule
import com.ardnn.carita.data.di.RemoteModule
import com.ardnn.carita.ui.addstory.AddStoryFragment
import com.ardnn.carita.ui.login.LoginActivity
import com.ardnn.carita.ui.main.MainActivity
import com.ardnn.carita.ui.signup.SignUpActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AppScope
@Singleton
@Component(modules = [
    ApplicationModule::class,
    ViewModelModule::class,
    LocalModule::class,
    RemoteModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)

    fun inject(activity: SignUpActivity)

    fun inject(activity: LoginActivity)

    fun inject(fragment: AddStoryFragment)
}