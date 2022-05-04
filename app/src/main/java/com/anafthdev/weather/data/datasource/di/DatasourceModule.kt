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
	
//	companion object {
//
//		@Singleton
//		@Provides
//		fun provideLocalDatasource(
//			@Named(DiName.DISPATCHER_IO) dispatcher: CoroutineDispatcher,
//			appDatastore: AppDatastore
//		): LocalDatasource = LocalDatasource(
//			dispatcher,
//			appDatastore
//		)
//
//		@Singleton
//		@Provides
//		fun provideRemoteDatasource(
//			@Named(DiName.DISPATCHER_IO) dispatcher: CoroutineDispatcher,
//			weatherService: WeatherService
//		): RemoteDatasource = RemoteDatasource(
//			dispatcher,
//			weatherService
//		)
//
//	}
	
}