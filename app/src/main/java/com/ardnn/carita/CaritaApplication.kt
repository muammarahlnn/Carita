package com.ardnn.carita

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.ardnn.carita.ui.di.ApplicationComponent
import com.ardnn.carita.ui.di.DaggerApplicationComponent
import timber.log.Timber

open class CaritaApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            applicationContext
        )
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}