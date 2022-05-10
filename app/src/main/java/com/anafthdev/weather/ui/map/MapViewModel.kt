package com.anafthdev.weather.ui.map

import androidx.lifecycle.viewModelScope
import com.anafthdev.weather.foundation.viewmodel.StatefulViewModel
import com.anafthdev.weather.ui.map.environment.IMapEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
	mapEnvironment: IMapEnvironment
): StatefulViewModel<MapState, Unit, MapAction, IMapEnvironment>(
	MapState(),
	mapEnvironment
) {
	
	init {
		viewModelScope.launch(environment.dispatcher) {
			environment.getCurrentLocation().collect { location ->
				setState {
					copy(
						location = location
					)
				}
			}
		}
	}
	
	override fun dispatch(action: MapAction) {
		when (action) {
			is MapAction.UpdateLocation -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.setCurrentLocation(action.location)
				}
			}
			is MapAction.InsertCity -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.insertCity(action.city)
				}
			}
		}
	}
}