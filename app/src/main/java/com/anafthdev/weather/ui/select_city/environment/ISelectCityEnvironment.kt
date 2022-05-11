package com.anafthdev.weather.ui.select_city.environment

import com.anafthdev.weather.model.geocoding.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface ISelectCityEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun setSelectedCity(city: City)
	
	fun getSelectedCity(): Flow<City>
	
	fun getAvailableCity(): Flow<List<City>>
	
}