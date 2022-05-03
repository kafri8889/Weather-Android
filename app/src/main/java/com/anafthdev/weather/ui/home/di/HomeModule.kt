package com.anafthdev.weather.ui.home.di

import com.anafthdev.weather.ui.home.environment.HomeEnvironment
import com.anafthdev.weather.ui.home.environment.IHomeEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {
	
	@Binds
	abstract fun provideEnvironment(
		homeEnvironment: HomeEnvironment
	): IHomeEnvironment
	
}