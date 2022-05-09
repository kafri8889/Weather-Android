package com.anafthdev.weather.data.networking.weather

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {
	
	private const val BASE_URL = "https://api.open-meteo.com/"
	
	fun createService(): WeatherService {
		val interceptor = HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}
		
		val client = OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.build()
		
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build().create(WeatherService::class.java)
	}
	
}