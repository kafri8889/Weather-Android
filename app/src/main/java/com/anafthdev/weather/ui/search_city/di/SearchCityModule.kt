package com.anafthdev.weather.ui.search_city.di

import com.anafthdev.weather.ui.search_city.environment.ISearchCityEnvironment
import com.anafthdev.weather.ui.search_city.environment.SearchCityEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchCityModule {
	
	@Binds
	abstract fun provideEnvironment(
		searchCityEnvironment: SearchCityEnvironment
	): ISearchCityEnvironment
	
}