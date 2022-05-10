package com.anafthdev.weather.ui.search_city

import androidx.lifecycle.viewModelScope
import com.anafthdev.weather.foundation.viewmodel.StatefulViewModel
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.ui.search_city.environment.ISearchCityEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
	searchCityEnvironment: ISearchCityEnvironment
): StatefulViewModel<SearchCityState, Unit, SearchCityAction, ISearchCityEnvironment>(
	SearchCityState(),
	searchCityEnvironment
){
	
	init {
		viewModelScope.launch(environment.dispatcher) {
			environment.getCity().collect { cities ->
				setState {
					copy(
						cities = cities ?: emptyList()
					)
				}
			}
		}
	}
	
	override fun dispatch(action: SearchCityAction) {
		when (action) {
			is SearchCityAction.Search -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.searchCity(action.q, action.language)
				}
			}
			is SearchCityAction.InsertCity -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.insertCity(*action.cities.toTypedArray())
				}
			}
		}
	}
	
}