package com.anafthdev.weather.data.networking.weather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

	@Singleton
	@Provides
	fun provideWeatherService(): WeatherService = WeatherClient.createService()

}