package com.anafthdev.weather.data.db.di

import android.content.Context
import androidx.room.Room
import com.anafthdev.weather.data.db.CityDatabase
import com.anafthdev.weather.data.db.dao.CityDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CityDatabaseModule {
	
	@Provides
	@Singleton
	fun provideCityDatabase(
		@ApplicationContext context: Context
	): CityDatabase = Room.databaseBuilder(
		context,
		CityDatabase::class.java,
		"city"
	).build()
	
	@Provides
	@Singleton
	fun provideCityDAO(
		cityDatabase: CityDatabase
	): CityDAO = cityDatabase.dao()
	
}