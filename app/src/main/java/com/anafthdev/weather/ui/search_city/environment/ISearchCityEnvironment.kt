package com.anafthdev.weather.ui.search_city.environment

import com.anafthdev.weather.model.geocoding.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface ISearchCityEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun searchCity(q: String, language: String)
	
	fun getCity(): Flow<List<City>?>
	
}