package com.ardnn.carita.data.main.repository.source.local

import android.content.Context
import com.ardnn.carita.data.main.repository.source.local.model.User
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

    fun getUser(): User = User().apply {
        with(preferences) {
            userId = getString(USER_ID, null)
            name = getString(USER_NAME, null)
            token = getString(USER_TOKEN, null)
        }
    }

    fun saveUser(user: User) {
        preferences.edit().apply {
            putString(USER_ID, user.userId)
            putString(USER_NAME, user.name)
            putString(USER_TOKEN, user.token)
            apply()
        }
    }

    fun logout() {
        preferences.edit().apply {
            putString(USER_ID, null)
            putString(USER_NAME, null)
            putString(USER_TOKEN, null)
            apply()
        }
    }

    companion object {

        private const val MAIN_PREF = "main_pref"
        private const val HAS_BEEN_LAUNCHED = "has_been_launched"
        private const val USER_ID = "user_id"
        private const val USER_NAME = "user_name"
        private const val USER_TOKEN = "user_token"
    }
}