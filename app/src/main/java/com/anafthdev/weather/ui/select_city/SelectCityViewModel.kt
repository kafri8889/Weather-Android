package com.anafthdev.weather.ui.select_city

import androidx.lifecycle.viewModelScope
import com.anafthdev.weather.foundation.viewmodel.StatefulViewModel
import com.anafthdev.weather.ui.select_city.environment.ISelectCityEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCityViewModel @Inject constructor(
	selectCityEnvironment: ISelectCityEnvironment
): StatefulViewModel<SelectCityState, Unit, SelectCityAction, ISelectCityEnvironment>(
	SelectCityState(),
	selectCityEnvironment
) {
	
	init {
		viewModelScope.launch(environment.dispatcher) {
			environment.getSelectedCity().collect { city ->
				setState {
					copy(
						selectedCity = city
					)
				}
			}
		}
	}
	
	override fun dispatch(action: SelectCityAction) {
		when (action) {
		
		}
	}
	
}