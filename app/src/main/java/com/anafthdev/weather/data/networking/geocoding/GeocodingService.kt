package com.anafthdev.weather.data.networking.geocoding

import com.anafthdev.weather.model.geocoding.Geocoding
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
	
	@GET("/v1/search?")
	fun search(
		@Query("name") q: String,
		@Query("language") lang: String = "en",
		@Query("count") count: Int = 20
	): Call<Geocoding>
	
}