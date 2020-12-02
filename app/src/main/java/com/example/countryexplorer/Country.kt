package com.example.countryexplorer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countryTable")
data class Country(
    @PrimaryKey(autoGenerate = true) val ID: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "area") val area: Int,
    @ColumnInfo(name = "subregion") val subregion: Int,
    @ColumnInfo(name = "alpha3Code") val alpha3Code: String
) {
}