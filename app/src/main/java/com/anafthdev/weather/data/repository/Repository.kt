package com.anafthdev.weather.data.repository

import com.anafthdev.weather.data.networking.WeatherClient
import com.anafthdev.weather.data.networking.WeatherService
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.weather.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class Repository @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher,
	private val weatherService: WeatherService
): IRepository {
	
	override suspend fun getWeather(lat: Double, lon: Double, apiKey: String): Flow<Weather> {
		val weather = MutableStateFlow(Weather.default)
		
		weatherService.getWeather(lat, lon, apiKey).enqueue(object : Callback<Weather> {
			override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
				if (response.isSuccessful) CoroutineScope(dispatcher).launch {
					weather.emit(response.body()!!)
					Timber.i("response success: ${response.body()}")
				} else {
					Timber.i("response failure: ${response.errorBody()?.string()}")
				}
			}
			
			override fun onFailure(call: Call<Weather>, t: Throwable) {
				Timber.i("response failure: ${t.message}")
			}
		})
		
		return weather
	}
	
}