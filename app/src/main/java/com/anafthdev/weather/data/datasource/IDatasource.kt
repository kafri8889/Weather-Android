package com.anafthdev.weather.data.datasource

import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IDatasource {
	
	val dispatcher: CoroutineDispatcher
	
	fun getWeather(lat: Double, lon: Double, apiKey: String): Flow<Weather>
	
}