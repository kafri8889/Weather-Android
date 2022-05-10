package com.anafthdev.weather.ui.map.data

import android.location.Location

interface LocationChangeListener {
	
	fun onLocationChanged(location: Location)
	
}