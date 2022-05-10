package com.anafthdev.weather.ui.search_city

import com.anafthdev.weather.model.geocoding.City

sealed class SearchCityAction {
	data class Search(
		val q: String,
		val language: String = "en"
	): SearchCityAction()
	
	data class InsertCity(val cities: List<City>): SearchCityAction()
}
