package com.example.countryexplorer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insertList(countryList: List<Country>)

    @Query("SELECT * FROM Country ORDER BY name ASC")
    abstract fun getAll(): Flow<List<Country>>

    @Query("SELECT * FROM Country WHERE numericCode LIKE :code LIMIT 1")
    abstract fun findByName(code: Int): Country

    @Query("DELETE FROM Country")
    suspend fun deleteAll()
}