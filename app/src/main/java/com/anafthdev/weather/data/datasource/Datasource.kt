package com.anafthdev.weather.data.datasource

import com.anafthdev.weather.data.datasource.local.LocalDatasource
import com.anafthdev.weather.data.datasource.remote.RemoteDatasource
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class Datasource @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val remoteDatasource: RemoteDatasource,
	private val localDatasource: LocalDatasource
): IDatasource {
	
	override fun getWeather(lat: Double, lon: Double, timezone: String): Flow<Weather> {
		return remoteDatasource.getWeather(
			lat = lat,
			lon = lon,
			timezone = timezone,
			onFailure = {
				localDatasource.getWeather().first()
			}
		)
	}
}