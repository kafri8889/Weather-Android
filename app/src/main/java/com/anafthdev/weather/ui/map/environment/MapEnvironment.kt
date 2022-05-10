package com.anafthdev.weather.ui.map.environment

import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.anafthdev.weather.foundation.di.DiName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class MapEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher
): IMapEnvironment {
	
	private val _currentLocation = MutableLiveData(Location(LocationManager.GPS_PROVIDER))
	private val currentLocation: LiveData<Location> = _currentLocation
	
	override suspend fun setCurrentLocation(location: Location) {
		_currentLocation.postValue(location)
	}
	
	override fun getCurrentLocation(): Flow<Location> {
		return currentLocation.asFlow()
	}
}