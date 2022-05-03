package com.anafthdev.weather.data.repository

import com.anafthdev.weather.data.networking.WeatherService
import com.anafthdev.weather.foundation.di.DiName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
	
	@Singleton
	@Provides
	fun provideRepository(
		weatherService: WeatherService,
		@Named(DiName.DISPATCHER_IO) dispatcher: CoroutineDispatcher
	): IRepository = Repository(dispatcher, weatherService)
	
}