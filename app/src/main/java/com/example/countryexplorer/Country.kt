package com.example.countryexplorer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class Country(
    @PrimaryKey@ColumnInfo(name = "numericCode") val numericCode: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "area") val area: Float,
    @ColumnInfo(name = "subregion") val subregion: String,
    @ColumnInfo(name = "alpha3Code") val alpha3Code: String
) {
}