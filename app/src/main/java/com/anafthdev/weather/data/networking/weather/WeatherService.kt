package com.anafthdev.weather.data.networking.weather

import com.anafthdev.weather.model.weather.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
	
	@GET("/v1/forecast")
	fun getWeather(
		@Query("latitude") lat: Double,
		@Query("longitude") lon: Double,
		@Query("timezone") timeZone: String,
		@Query("hourly", encoded = true) hourly: String = "temperature_2m,relativehumidity_2m,apparent_temperature,pressure_msl,precipitation,weathercode,cloudcover,vapor_pressure_deficit,windspeed_10m",
		@Query("timeformat") timeFormat: String = "unixtime"
	): Call<Weather>

}