package com.anafthdev.weather.ui.home

sealed class HomeAction {
	data class GetWeather(val lat: Double, val lon: Double, val timezone: String): HomeAction()
}
