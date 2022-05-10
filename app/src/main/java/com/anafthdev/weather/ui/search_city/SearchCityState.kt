package com.anafthdev.weather.ui.search_city

import com.anafthdev.weather.model.geocoding.City

data class SearchCityState(
	val cities: List<City> = emptyList()
)
