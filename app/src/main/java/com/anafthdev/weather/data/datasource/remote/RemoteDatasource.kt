package com.anafthdev.weather.data.datasource.remote

import com.anafthdev.weather.data.datasource.IDatasource
import com.anafthdev.weather.data.networking.WeatherService
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class RemoteDatasource @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val weatherService: WeatherService
): IRemoteDatasource {
	
	override fun getWeather(
		lat: Double,
		lon: Double,
		apiKey: String,
		onFailure: suspend () -> Weather
	): Flow<Weather> {
		val weather = MutableStateFlow(Weather.default)
		
		weatherService.getWeather(lat, lon, apiKey).enqueue(object : Callback<Weather> {
			override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
				CoroutineScope(dispatcher).launch {
					if (response.isSuccessful) {
						weather.emit(response.body()!!)
						Timber.i("response success: ${response.body()}")
					} else {
						weather.emit(onFailure())
						Timber.i("response failure: ${response.message()}")
					}
				}
			}
			
			override fun onFailure(call: Call<Weather>, t: Throwable) {
				Timber.i("response failure: ${t.message}")
				
				CoroutineScope(dispatcher).launch {
					weather.emit(onFailure())
				}
			}
		})
		
		return weather
	}
}