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
import javax.inject.Inject

interface Repository {
    val listOfCountries: Flow<List<Country>>
    suspend fun refresh()
    fun getCountry(Code : Int) : Country
}

class RepositoryImp(
    private val apiService: ApiService?,
    private val countryDao: CountryDao
) : Repository{

    override var listOfCountries: Flow<List<Country>> = countryDao.getAll()

    override suspend fun refresh() {
        countryDao.deleteAll()
        val response = apiService?.getAllCountries()
        Log.d("Debug", response.toString())
        if (response != null) {
            countryDao.insertList(response)
        }
    }

    override fun getCountry(code: Int) : Country{
        return countryDao.findByName(code)
    }
}
