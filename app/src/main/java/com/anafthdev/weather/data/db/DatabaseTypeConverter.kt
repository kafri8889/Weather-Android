package com.anafthdev.weather.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object DatabaseTypeConverter {
	
	@TypeConverter
	fun stringListToJSON(list: List<String>?): String? = Gson().toJson(list)
	
	@TypeConverter
	fun stringListFromJSON(json: String): List<String> =
		Gson().fromJson(json, object : TypeToken<List<String>>() {}.type) ?: emptyList()
	
}