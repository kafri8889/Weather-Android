package com.anafthdev.weather.ui.home.environment

import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IHomeEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun getWeather(lat: Double, lon: Double, apiKey: String)
	
	suspend fun getWeather(): Flow<Weather>
	
}