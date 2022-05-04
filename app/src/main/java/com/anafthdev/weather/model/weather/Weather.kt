package com.anafthdev.weather.model.weather

import com.google.gson.Gson

data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
) {
    
    fun toJSON(): String {
        return Gson().toJson(this)
    }
    
    companion object {
        val default = Weather(
            base = "",
            cod = -1,
            id = -1,
            dt = -1,
            name = "",
            timezone = -1,
            visibility = -1,
            clouds = Clouds.default,
            coord = Coord.default,
            main = Main.default,
            sys = Sys.default,
            wind = Wind.default,
            weather = listOf(WeatherX.default)
        )
    
        fun fromJSON(src: String): Weather {
            return Gson().fromJson(src, Weather::class.java)
        }
    }
}