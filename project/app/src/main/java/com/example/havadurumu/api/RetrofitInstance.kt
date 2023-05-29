package com.example.havadurumu.api

import com.example.havadurumu.api.contract.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.collectapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }
}