package com.ardnn.carita.data.main.repository.source.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.rxjava2.RxDataStore
import javax.inject.Inject

class MainPreference @Inject constructor(context: Context) {

    private val preferences = context.getSharedPreferences(MAIN_PREF, Context.MODE_PRIVATE)

    fun getHasBeenLaunched(): Boolean =
        preferences.getBoolean(HAS_BEEN_LAUNCHED, false)

    fun saveHasBeenLaunched() {
        preferences.edit().apply() {
            putBoolean(HAS_BEEN_LAUNCHED, true)
            apply()
        }
    }

    companion object {

        private const val MAIN_PREF = "main_pref"
        private const val HAS_BEEN_LAUNCHED = "has_been_launched"
    }
}