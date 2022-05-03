package com.anafthdev.weather.data.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {
	
	private const val BASE_URL = "https://api.openweathermap.org/"
	
	fun createService(): WeatherService {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build().create(WeatherService::class.java)
	}
	
}