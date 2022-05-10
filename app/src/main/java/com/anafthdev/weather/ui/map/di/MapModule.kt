package com.anafthdev.weather.ui.map.di

import com.anafthdev.weather.ui.map.environment.IMapEnvironment
import com.anafthdev.weather.ui.map.environment.MapEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapModule {
	
	@Binds
	abstract fun provideEnvironment(
		selectCityEnvironment: MapEnvironment
	): IMapEnvironment
	
}