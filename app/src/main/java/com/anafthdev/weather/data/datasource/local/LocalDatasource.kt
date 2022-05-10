package com.anafthdev.weather.data.datasource.local

import com.anafthdev.weather.data.datasource.IDatasource
import com.anafthdev.weather.data.datastore.AppDatastore
import com.anafthdev.weather.data.db.dao.CityDAO
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class LocalDatasource @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val datastore: AppDatastore,
	private val cityDAO: CityDAO
): ILocalDatasource {
	
	override fun getWeather(): Flow<Weather> {
		return datastore.getWeather
	}
	
	override fun getAvailableCity(): Flow<List<City>> {
		return cityDAO.getAllCity()
	}
	
}