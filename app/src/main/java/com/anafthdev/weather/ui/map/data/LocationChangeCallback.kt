package com.anafthdev.weather.ui.map.data

import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineResult
import timber.log.Timber
import java.lang.Exception

class LocationChangeCallback(
	private val listener: LocationChangeListener
): LocationEngineCallback<LocationEngineResult> {
	
	override fun onSuccess(result: LocationEngineResult?) {
		result?.let { locationEngineResult ->
			locationEngineResult.lastLocation?.let { lastLocation ->
				listener.onLocationChanged(lastLocation)
			}
		}
	}
	
	override fun onFailure(p0: Exception) {
		Timber.e(p0)
	}
	
}