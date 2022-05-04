package com.anafthdev.weather.data.datasource.local

import com.anafthdev.weather.data.datasource.IDatasource
import com.anafthdev.weather.data.datastore.AppDatastore
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class LocalDatasource @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val datastore: AppDatastore
): ILocalDatasource {
	
	override fun getWeather(): Flow<Weather> {
		return datastore.getWeather
	}
}