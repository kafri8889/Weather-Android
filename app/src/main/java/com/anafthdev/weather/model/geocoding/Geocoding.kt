package com.anafthdev.weather.model.geocoding

data class Geocoding(
    val generationtime_ms: Double,
    val results: List<City>
) {
    
    companion object {
        val default = Geocoding(
            generationtime_ms = 0.0,
            results = emptyList()
        )
    }
    
}