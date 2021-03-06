package com.anafthdev.weather.data.datasource.remote

import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IRemoteDatasource {
	
	val dispatcher: CoroutineDispatcher
	
	fun getWeather(lat: Double, lon: Double, timezone: String, onFailure: suspend () -> Weather): Flow<Weather>
	
	fun searchCity(q: String, language: String, onFailure: suspend () -> List<City>): Flow<List<City>>
	
}