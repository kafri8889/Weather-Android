package com.anafthdev.weather.model.weather

data class HourlyUnits(
    val apparent_temperature: String,
    val precipitation: String,
    val weathercode: String,
    val cloudcover: String,
    val pressure_msl: String,
    val relativehumidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val vapor_pressure_deficit: String,
    val windspeed_10m: String
) {
    
    companion object {
        val default = HourlyUnits(
            apparent_temperature = "",
            precipitation = "",
            weathercode = "",
            cloudcover = "",
            pressure_msl = "",
            relativehumidity_2m = "",
            temperature_2m = "",
            time = "",
            vapor_pressure_deficit = "",
            windspeed_10m = "",
        )
    }
    
}