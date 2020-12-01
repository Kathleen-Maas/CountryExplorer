package com.example.countryexplorer

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        val BASE_URL: String = "https://restcountries.eu/rest/v2/"
    }
    @GET("all")
    suspend fun getAllCountries(): List<Country>
}