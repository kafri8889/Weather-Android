package com.anafthdev.weather.data.repository

import com.anafthdev.weather.data.datasource.IDatasource
import com.anafthdev.weather.data.db.dao.CityDAO
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class Repository @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val datasource: IDatasource
): IRepository {
	
	override fun getWeather(lat: Double, lon: Double, timezone: String): Flow<Weather> {
		return datasource.getWeather(lat, lon, timezone)
	}
	
	override fun searchCity(q: String, language: String): Flow<List<City>> {
		return datasource.searchCity(q, language)
	}
	
	override fun getAvailableCity(): Flow<List<City>> {
		return datasource.getAvailableCity()
	}
	
	override suspend fun insertCity(vararg city: City) {
		datasource.insertCity(*city)
	}
	
}