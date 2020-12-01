package com.example.countryexplorer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainScreenVamp(val repository: Repository) : ViewModel() {

    val countries = repository.listOfCountries.asLiveData()

    fun onRefreshTapped(){
        Log.d("Debug", "Getting tap")
        viewModelScope.launch {
            repository.refresh()
        }
    }
}