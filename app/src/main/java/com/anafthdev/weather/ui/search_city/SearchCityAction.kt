package com.anafthdev.weather.ui.search_city

sealed class SearchCityAction {
	data class Search(
		val q: String,
		val language: String = "en"
	): SearchCityAction()
}
