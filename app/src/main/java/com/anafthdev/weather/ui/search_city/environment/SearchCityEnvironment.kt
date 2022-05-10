package com.anafthdev.weather.ui.search_city.environment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.anafthdev.weather.data.repository.IRepository
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.geocoding.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Named

class SearchCityEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_MAIN) override val dispatcher: CoroutineDispatcher,
	private val repository: IRepository
): ISearchCityEnvironment {
	
	private val _cities = MutableLiveData(emptyList<City>())
	private val cities: LiveData<List<City>> = _cities
	
	override suspend fun searchCity(q: String, language: String) {
		repository.searchCity(q, language).collect { results ->
			_cities.postValue(results)
		}
	}
	
	override fun getCity(): Flow<List<City>?> {
		return cities.asFlow()
	}
	
}