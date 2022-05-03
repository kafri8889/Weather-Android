package com.anafthdev.weather.foundation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DiModule {
	
	@Provides
	@Named(DiName.DISPATCHER_IO)
	fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
	
	@Provides
	@Named(DiName.DISPATCHER_MAIN)
	fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main
	
}