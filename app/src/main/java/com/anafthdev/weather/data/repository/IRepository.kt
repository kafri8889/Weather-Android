package com.anafthdev.weather.data.repository

import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IRepository {
	
	val dispatcher: CoroutineDispatcher
	
	fun getWeather(lat: Double, lon: Double, timezone: String): Flow<Weather>
	
	fun searchCity(q: String, language: String): Flow<List<City>>
	
	fun getAvailableCity(): Flow<List<City>>
	
	suspend fun insertCity(vararg city: City)
	
}