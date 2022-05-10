package com.anafthdev.weather.ui.map

import android.location.Location
import com.anafthdev.weather.model.geocoding.City

sealed class MapAction {
	data class UpdateLocation(val location: Location): MapAction()
	data class InsertCity(val city: City): MapAction()
}
