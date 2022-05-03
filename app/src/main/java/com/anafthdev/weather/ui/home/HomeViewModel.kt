package com.anafthdev.weather.ui.home

import androidx.lifecycle.viewModelScope
import com.anafthdev.weather.foundation.viewmodel.StatefulViewModel
import com.anafthdev.weather.ui.home.environment.IHomeEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	homeEnvironment: IHomeEnvironment
): StatefulViewModel<HomeState, Unit, HomeAction, IHomeEnvironment>(HomeState(), homeEnvironment) {
	
	init {
		viewModelScope.launch(environment.dispatcher) {
			environment.getWeather().collect { weather ->
				setState {
					copy(
						weather = weather
					)
				}
			}
		}
	}
	
	override fun dispatch(action: HomeAction) {
		when (action) {
			is HomeAction.GetWeather -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.getWeather(
						lat = action.lat,
						lon = action.lon,
						apiKey = action.apiKey
					)
				}
			}
		}
	}
}