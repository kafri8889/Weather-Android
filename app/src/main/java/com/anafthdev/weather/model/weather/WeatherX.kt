package com.anafthdev.weather.model.weather

data class WeatherX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) {
    companion object {
        val default = WeatherX(
            id = -1,
            main = "",
            description = "",
            icon = ""
        )
    }
}