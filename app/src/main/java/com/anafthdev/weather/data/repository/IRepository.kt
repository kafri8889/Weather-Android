package com.anafthdev.weather.data.repository

import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IRepository {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun getWeather(lat: Double, lon: Double, apiKey: String): Flow<Weather>
	
}