package com.anafthdev.weather.data.datasource.remote

import com.anafthdev.weather.data.networking.geocoding.GeocodingService
import com.anafthdev.weather.data.networking.weather.WeatherService
import com.anafthdev.weather.foundation.di.DiName
import com.anafthdev.weather.model.geocoding.Geocoding
import com.anafthdev.weather.model.geocoding.City
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
	private val weatherService: WeatherService,
	private val geocodingService: GeocodingService
): IRemoteDatasource {
	
	override fun getWeather(
		lat: Double,
		lon: Double,
		timezone: String,
		onFailure: suspend () -> Weather
	): Flow<Weather> {
		val weather = MutableStateFlow(Weather.default)
		
		weatherService.getWeather(lat, lon, timezone).enqueue(object : Callback<Weather> {
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
	
	override fun searchCity(
		q: String,
		language: String,
		onFailure: suspend () -> List<City>
	): Flow<List<City>> {
		val cities = MutableStateFlow(emptyList<City>())
		
		geocodingService.search(q,  language).enqueue(object : Callback<Geocoding> {
			override fun onResponse(call: Call<Geocoding>, response: Response<Geocoding>) {
				CoroutineScope(dispatcher).launch {
					if (response.isSuccessful) {
						cities.emit(response.body()!!.results)
						Timber.i("response success: ${response.body()}")
					} else {
						cities.emit(onFailure())
						Timber.i("response failure: ${response.message()}")
					}
				}
			}
			
			override fun onFailure(call: Call<Geocoding>, t: Throwable) {
				Timber.i("response failure: ${t.message}")
				
				CoroutineScope(dispatcher).launch {
					cities.emit(onFailure())
				}
			}
		})
		
		return cities
	}
}