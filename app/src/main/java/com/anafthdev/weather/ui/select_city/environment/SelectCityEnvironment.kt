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
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class SelectCityEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val appDatastore: AppDatastore,
	private val repository: IRepository
): ISelectCityEnvironment {
	
	private val _selectedCity = MutableLiveData(City.default)
	private val selectedCity: LiveData<City> = _selectedCity
	
	private val availableCity: ArrayList<City> = arrayListOf()
	
	init {
		CoroutineScope(dispatcher).launch {
			appDatastore.getSelectedCityID.collect { id ->
				_selectedCity.postValue(
					availableCity.get { it.id == id } ?: City.default
				)
			}
		}
		
		CoroutineScope(dispatcher).launch {
			repository.getAvailableCity().collect { cities ->
				availableCity.apply {
					clear()
					addAll(cities)
				}
			}
		}
	}
	
	override suspend fun getSelectedCity(): Flow<City> {
		return selectedCity.asFlow()
	}
	
	override suspend fun setSelectedCity(city: City) {
		appDatastore.setSelectedCityID(city.id)
	}
}