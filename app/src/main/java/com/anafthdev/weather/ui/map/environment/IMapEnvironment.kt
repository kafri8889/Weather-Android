package com.anafthdev.weather.ui.map.environment

import android.location.Location
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IMapEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun setCurrentLocation(location: Location)
	
	fun getCurrentLocation(): Flow<Location>
	
}