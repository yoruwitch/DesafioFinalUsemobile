package com.example.desafiofinal.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.desafiofinal.RecyclerData

object DataStoreManager {

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        "FAVOURITES"
    )

    suspend fun setFavouriteDataStore(data: RecyclerData) {
        application.preferencesDataStore.edit { preferences ->
           // preferences[parc]
        }
    }
}