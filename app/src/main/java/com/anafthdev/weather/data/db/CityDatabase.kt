package com.anafthdev.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anafthdev.weather.data.db.dao.CityDAO
import com.anafthdev.weather.model.geocoding.City

@Database(
	entities = [
		City::class
	],
	version = 1,
	exportSchema = false
)
@TypeConverters(DatabaseTypeConverter::class)
abstract class CityDatabase: RoomDatabase() {
	abstract fun dao(): CityDAO
}