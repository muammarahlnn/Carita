package com.ardnn.carita.data.main.repository.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ardnn.carita.data.main.repository.source.local.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MainPreferences, 18/01/2023 19.32 by Muammar Ahlan Abimanyu
 */
class MainPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val HAS_BEEN_LAUNCHED = booleanPreferencesKey("has_been_launched")

    private val USER_ID = stringPreferencesKey("user_id")

    private val USER_NAME = stringPreferencesKey("user_name")

    private val USER_TOKEN = stringPreferencesKey("user_token")

    fun getHasBeenLaunched(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[HAS_BEEN_LAUNCHED] ?: false
        }

    suspend fun saveHasBeenLaunched() {
        dataStore.edit { preferences ->
            preferences[HAS_BEEN_LAUNCHED] = true
        }
    }

    fun getUser(): Flow<User> =
        dataStore.data.map { preferences ->
            User(
                preferences[USER_ID] ?: "",
                preferences[USER_NAME] ?: "",
                preferences[USER_TOKEN] ?: ""
            )
        }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = user.userId
            preferences[USER_NAME] = user.name
            preferences[USER_TOKEN] = user.token
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[USER_ID] = ""
            preferences[USER_NAME] = ""
            preferences[USER_TOKEN] = ""
        }
    }
}