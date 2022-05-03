package com.anafthdev.weather.model.weather

data class Coord(
    val lat: Double,
    val lon: Double
) {
    companion object {
        val default = Coord(
            lat = -1.0,
            lon = -1.0
        )
    }
}