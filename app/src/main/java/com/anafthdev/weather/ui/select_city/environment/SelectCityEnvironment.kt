package com.anafthdev.weather.ui.select_city.environment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.anafthdev.weather.data.datastore.AppDatastore
import com.anafthdev.weather.data.repository.IRepository
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.foundation.extension.get
import com.anafthdev.weather.model.geocoding.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class SelectCityEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val appDatastore: AppDatastore,
	private val repository: IRepository
): ISelectCityEnvironment {
	
	private val _selectedCity = MutableLiveData(City.default)
	private val selectedCity: LiveData<City> = _selectedCity
	
	private val _availableCity = MutableLiveData(emptyList<City>())
	private val availableCity: LiveData<List<City>> = _availableCity
	
	init {
		CoroutineScope(dispatcher).launch {
			repository.getAvailableCity().combine(appDatastore.getSelectedCityID) { cities, id ->
				cities to id
			}.collect { pair ->
				_availableCity.postValue(pair.first)
				_selectedCity.postValue(
					pair.first.get { it.id == pair.second } ?: City.default
				)
			}
		}
	}
	
	override suspend fun getSelectedCity(): Flow<City> {
		return selectedCity.asFlow()
	}
	
	override suspend fun setSelectedCity(city: City) {
		appDatastore.setSelectedCityID(city.id)
	}
	
	override fun getAvailableCity(): Flow<List<City>> {
		return availableCity.asFlow()
	}
}