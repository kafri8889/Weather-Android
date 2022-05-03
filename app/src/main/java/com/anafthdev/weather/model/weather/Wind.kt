package com.anafthdev.weather.model.weather

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
) {
    companion object {
        val default = Wind(
            deg = -1,
            gust = -1.0,
            speed = -1.0
        )
    }
}
