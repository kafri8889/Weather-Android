package com.anafthdev.weather.ui.home

import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.model.weather.Weather

data class HomeState(
	val weather: Weather = Weather.default,
	val selectedCity: City = City.default
)
