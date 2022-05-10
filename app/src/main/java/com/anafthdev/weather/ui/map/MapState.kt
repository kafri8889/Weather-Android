package com.anafthdev.weather.ui.map

import android.location.Location
import android.location.LocationManager

data class MapState(
	val location: Location = Location(LocationManager.GPS_PROVIDER)
)
