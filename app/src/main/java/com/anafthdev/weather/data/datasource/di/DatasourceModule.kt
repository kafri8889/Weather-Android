package com.anafthdev.weather.data.datasource.di

import com.anafthdev.weather.data.datasource.Datasource
import com.anafthdev.weather.data.datasource.IDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {
	
	@Binds
	abstract fun provideDatasource(
		datasource: Datasource
	): IDatasource
	
}