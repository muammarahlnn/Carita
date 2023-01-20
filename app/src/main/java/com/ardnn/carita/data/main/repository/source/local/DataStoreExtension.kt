package com.ardnn.carita.data.main.repository.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DataStoreExtension, 18/01/2023 20.04 by Muammar Ahlan Abimanyu
 */
val Context.mainDataStore: DataStore<Preferences> by preferencesDataStore("main")