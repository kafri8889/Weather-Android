package com.anafthdev.weather.ui.select_city

import com.anafthdev.weather.model.geocoding.City

data class SelectCityState(
	val selectedCity: City = City.default,
	val availableCities: List<City> = emptyList()
)
