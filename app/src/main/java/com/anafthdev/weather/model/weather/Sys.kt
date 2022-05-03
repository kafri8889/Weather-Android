package com.anafthdev.weather.model.weather

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
) {
    companion object {
        val default = Sys(
            country = "",
            id = -1,
            sunrise = -1,
            sunset = -1,
            type = -1
        )
    }
}