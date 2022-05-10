package com.anafthdev.weather.data.db.dao

import androidx.room.*
import com.anafthdev.weather.model.geocoding.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDAO {
	
	@Query("SELECT * FROM city_table")
	fun getAllCity(): Flow<List<City>>
	
	@Delete
	fun delete(vararg city: City)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg city: City)
	
}