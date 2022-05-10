package com.anafthdev.weather.data.networking.geocoding

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeocodingModule {
	
	@Provides
	@Singleton
	fun provideGeocodingService(): GeocodingService = GeocodingClient.createService()
	
}