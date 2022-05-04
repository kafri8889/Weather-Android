package com.anafthdev.weather.data.datastore

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {
	
	@Singleton
	@Provides
	fun provideAppDatastore(
		@ApplicationContext context: Context
	): AppDatastore = AppDatastore(context)
	
}