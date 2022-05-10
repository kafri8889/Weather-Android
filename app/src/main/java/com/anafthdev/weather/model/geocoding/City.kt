package com.anafthdev.weather.model.geocoding

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "admin1") val admin1: String,
    @ColumnInfo(name = "admin1_id") val admin1_id: Int,
    @ColumnInfo(name = "admin2") val admin2: String,
    @ColumnInfo(name = "admin2_id") val admin2_id: Int,
    @ColumnInfo(name = "admin3") val admin3: String,
    @ColumnInfo(name = "admin3_id") val admin3_id: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "country_code") val country_code: String,
    @ColumnInfo(name = "country_id") val country_id: Int,
    @ColumnInfo(name = "elevation") val elevation: Double,
    @ColumnInfo(name = "feature_code") val feature_code: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "postcodes") val postcodes: List<String>,
    @ColumnInfo(name = "ranking") val ranking: Double,
    @ColumnInfo(name = "timezone") val timezone: String
) {
    
    companion object {
        val default = City(
            id = 0,
            admin1 = "",
            admin1_id = 0,
            admin2 = "",
            admin2_id = 0,
            admin3 = "",
            admin3_id = 0,
            country = "",
            country_code = "",
            country_id = 0,
            elevation = 0.0,
            feature_code = "",
            latitude = 0.0,
            longitude = 0.0,
            name = "",
            population = 0,
            postcodes = emptyList(),
            ranking = 0.0,
            timezone = ""
        )
    }
    
}