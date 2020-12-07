package com.example.countryexplorer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class CountryVamp @ViewModelInject constructor(private val repository: Repository) : ViewModel()  {

    fun getCountry(code : Int) : Country {
        return repository.getCountry(code)
    }
}