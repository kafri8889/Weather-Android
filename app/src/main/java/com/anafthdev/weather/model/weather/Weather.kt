package com.anafthdev.weather.model.weather

import com.google.gson.Gson

data class Weather(
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val utc_offset_seconds: Int
) {
    
    fun toJSON(): String {
        return Gson().toJson(this)
    }
    
    companion object {
        val default = Weather(
            elevation = 0.0,
            generationtime_ms = 0.0,
            hourly = Hourly.default,
            hourly_units = HourlyUnits.default,
            latitude = 0.0,
            longitude = 0.0,
            utc_offset_seconds = 0
        )
        
        fun fromJSON(src: String): Weather {
            return Gson().fromJson(src, Weather::class.java)
        }
    }
}