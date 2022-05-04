package com.anafthdev.weather.data.datasource.di

import com.anafthdev.weather.data.datasource.local.ILocalDatasource
import com.anafthdev.weather.data.datasource.local.LocalDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDatasourceModule {
	
	@Binds
	abstract fun provideLocalDatasource(
		localDatasource: LocalDatasource
	): ILocalDatasource
	
}