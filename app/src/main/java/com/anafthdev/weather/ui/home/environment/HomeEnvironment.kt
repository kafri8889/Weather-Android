package com.anafthdev.weather.ui.home.environment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.anafthdev.weather.data.datastore.AppDatastore
import com.anafthdev.weather.data.repository.IRepository
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.foundation.extension.get
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class HomeEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val appDatastore: AppDatastore,
	private val repository: IRepository
): IHomeEnvironment {
	
	private val _weather = MutableLiveData(Weather.default)
	private val weather: LiveData<Weather> = _weather
	
	private val _selectedCity = MutableLiveData(City.default)
	private val selectedCity: LiveData<City> = _selectedCity
	
	init {
		CoroutineScope(dispatcher).launch {
			repository.getAvailableCity().combine(appDatastore.getSelectedCityID) { cities, id ->
				cities to id
			}.collect { pair ->
				_selectedCity.postValue(
					pair.first.get { it.id == pair.second } ?: City.default
				)
			}
		}
	}
	
	override suspend fun getWeather(lat: Double, lon: Double, timezone: String) {
		repository.getWeather(
			lat = lat,
			lon = lon,
			timezone = timezone
		).collect {
			_weather.postValue(it)
			appDatastore.setWeather(it.toJSON())
		}
	}
	
	override fun getWeather(): Flow<Weather> {
		return weather.asFlow()
	}
	
	override fun getSelectedCity(): Flow<City> {
		return selectedCity.asFlow()
	}
	
}