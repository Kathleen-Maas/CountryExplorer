package com.example.countryexplorer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertList(countryList: List<Country>)

    @Query("SELECT * FROM countryTable ORDER BY name ASC")
    abstract fun getAll(): Flow<List<Country>>

    @Query("SELECT * FROM countryTable WHERE name LIKE :countryName LIMIT 1")
    abstract fun findByName(countryName: String): Country

}