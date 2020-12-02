package com.example.countryexplorer

import android.content.SharedPreferences
import android.util.Log
import io.reactivex.annotations.SchedulerSupport.IO
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import okhttp3.Dispatcher

interface Repository {
    val listOfCountries: Flow<List<Country>>
    suspend fun refresh()
}

class RepositoryImp(
    private val apiService: ApiService?,
    //private val countryDao: CountryDao
) : Repository{

    override var listOfCountries: Flow<List<Country>> = flowOf(emptyList())//countryDao.getAll()

    override suspend fun refresh() {
        val response = apiService?.getAllCountries()

//        if (response != null) {
//            countryDao.insertList(response)
//        }
    }
}
