package com.anafthdev.weather.ui.select_city.di

import com.anafthdev.weather.ui.select_city.environment.ISelectCityEnvironment
import com.anafthdev.weather.ui.select_city.environment.SelectCityEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SelectCityModule {
	
	@Binds
	abstract fun provideEnvironment(
		selectCityEnvironment: SelectCityEnvironment
	): ISelectCityEnvironment
	
}