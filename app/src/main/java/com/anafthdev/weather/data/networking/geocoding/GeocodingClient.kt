package com.anafthdev.weather.data.networking.geocoding

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocodingClient {
	
	private const val BASE_URL = "https://geocoding-api.open-meteo.com/"
	private const val FLAG_IMAGE_URL = "https://open-meteo.com/images/country-flags/"
	
	fun createService(): GeocodingService {
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
			.build().create(GeocodingService::class.java)
	}
	
	fun getFlagImage(countryCode: String): String {
		return "${FLAG_IMAGE_URL}$countryCode.svg"
	}

}