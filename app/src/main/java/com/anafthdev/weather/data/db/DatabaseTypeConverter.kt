package com.anafthdev.weather.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson

object DatabaseTypeConverter {
	
	@TypeConverter
	fun stringListToJSON(list: List<String>) = Gson().toJson(list)!!
	
	@TypeConverter
	fun stringListFromJSON(json: String) = Gson().fromJson(json, Array<String>::class.java).toList()
	
}