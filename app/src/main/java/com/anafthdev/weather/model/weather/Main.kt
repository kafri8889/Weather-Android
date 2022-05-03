package com.anafthdev.weather.model.weather

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) {
    companion object {
        val default = Main(
            feels_like = -1.0,
            humidity = -1,
            pressure = -1,
            temp = -1.0,
            temp_max = -1.0,
            temp_min = -1.0
        )
    }
}