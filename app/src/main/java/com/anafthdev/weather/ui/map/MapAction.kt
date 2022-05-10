package com.anafthdev.weather.ui.map

import android.location.Location

sealed class MapAction {
	data class UpdateLocation(val location: Location): MapAction()
}
