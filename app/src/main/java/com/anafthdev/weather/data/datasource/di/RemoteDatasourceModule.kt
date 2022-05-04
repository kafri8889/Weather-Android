package com.anafthdev.weather.data.datasource.di

import com.anafthdev.weather.data.datasource.remote.IRemoteDatasource
import com.anafthdev.weather.data.datasource.remote.RemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDatasourceModule {
	
	@Binds
	abstract fun provideRemoteDatasource(
		localDatasource: RemoteDatasource
	): IRemoteDatasource
	
}