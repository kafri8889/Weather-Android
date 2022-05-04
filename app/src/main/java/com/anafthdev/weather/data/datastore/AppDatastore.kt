package com.anafthdev.weather.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.anafthdev.weather.data.Preference
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDatastore @Inject constructor(private val context: Context) {
	
	suspend fun setWeather(mWeather: String) {
		context.datastore.edit { preferences ->
			preferences[weather] = mWeather
		}
	}
	
	val getWeather: Flow<Weather> = context.datastore.data.map { preferences ->
		preferences[weather]?.let {
			Weather.fromJSON(it)
		} ?: Weather.default
	}
	
	companion object {
		val Context.datastore: DataStore<Preferences> by preferencesDataStore("app_datastore")
		
		val weather = stringPreferencesKey(Preference.WEATHER)
	}
	
}