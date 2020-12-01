package com.example.countryexplorer

import android.content.SharedPreferences
import android.util.Log
import io.reactivex.annotations.SchedulerSupport.IO
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Dispatcher

interface Repository {
    val listOfCountries: MutableStateFlow<List<Country>>
    suspend fun refresh()
}

class RepositoryImp(
    private val apiService: ApiService?
) : Repository{

    override var listOfCountries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())

    override suspend fun refresh() {
        val response = apiService?.getAllCountries()

        if (response != null) {
            listOfCountries.value = response
        }
    }
}
