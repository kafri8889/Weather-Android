package com.anafthdev.weather.data.repository

import com.anafthdev.weather.data.networking.WeatherService
import com.anafthdev.weather.foundation.di.DiName
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
	
	@Binds
	abstract fun provideRepository(
		repository: Repository
	): IRepository
	
}