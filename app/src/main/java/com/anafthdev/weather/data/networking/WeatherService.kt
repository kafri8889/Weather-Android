package com.anafthdev.weather.data.networking

import com.anafthdev.weather.model.weather.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
	
	@GET("/data/2.5/weather")
	fun getWeather(
		@Query("lat") lat: Double,
		@Query("lon") lon: Double,
		@Query("appid") apiKey: String
	): Call<Weather>

}