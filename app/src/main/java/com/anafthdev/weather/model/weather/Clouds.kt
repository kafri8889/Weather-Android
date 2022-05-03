package com.anafthdev.weather.model.weather

data class Clouds(
    val all: Int
) {
    companion object {
        val default = Clouds(
            all = -1
        )
    }
}