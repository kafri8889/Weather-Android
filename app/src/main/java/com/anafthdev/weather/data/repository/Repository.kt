package com.anafthdev.weather.data.repository

import com.anafthdev.weather.data.datasource.IDatasource
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class Repository @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val datasource: IDatasource
): IRepository {
	
	override suspend fun getWeather(lat: Double, lon: Double, timezone: String): Flow<Weather> {
		return datasource.getWeather(lat, lon, timezone)
	}
	
}