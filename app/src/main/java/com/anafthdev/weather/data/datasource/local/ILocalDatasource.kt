package com.anafthdev.weather.data.datasource.local

import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface ILocalDatasource {
	
	val dispatcher: CoroutineDispatcher
	
	fun getWeather(): Flow<Weather>
	
}