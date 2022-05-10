package com.anafthdev.weather.ui.map.environment

import android.location.Location
import com.anafthdev.weather.model.geocoding.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IMapEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun setCurrentLocation(location: Location)
	
	suspend fun insertCity(city: City)
	
	fun getCurrentLocation(): Flow<Location>
	
}