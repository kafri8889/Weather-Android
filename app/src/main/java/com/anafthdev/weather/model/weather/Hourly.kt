package com.anafthdev.weather.model.weather

data class Hourly(
    val apparent_temperature: List<Double>,
    val precipitation: List<Double>,
    val weathercode: List<Int>,
    val cloudcover: List<Int>,
    val pressure_msl: List<Double>,
    val relativehumidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<Int>,
    val vapor_pressure_deficit: List<Double>,
    val windspeed_10m: List<Double>
) {
    
    companion object {
        val default = Hourly(
            apparent_temperature = emptyList(),
            precipitation = emptyList(),
            weathercode = emptyList(),
            cloudcover = emptyList(),
            pressure_msl = emptyList(),
            relativehumidity_2m = emptyList(),
            temperature_2m = emptyList(),
            time = emptyList(),
            vapor_pressure_deficit = emptyList(),
            windspeed_10m = emptyList(),
        )
    }
    
}