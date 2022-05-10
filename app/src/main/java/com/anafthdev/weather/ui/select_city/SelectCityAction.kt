package com.anafthdev.weather.ui.select_city

import com.anafthdev.weather.model.geocoding.City

sealed class SelectCityAction {
	data class SetSelectedCity(val city: City): SelectCityAction()
}
