package com.example.havadurumu.api.contract

import com.example.havadurumu.api.models.MyWeather
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("authorization: apikey 4JAB5L3Sv5pvORDBNGWCLv:7sS8PFTv6PBg73DRC3JN2E")
    @GET("/weather/getWeather")
    suspend fun fetchWeather(
        @Query("data.lang")lang:String,
        @Query("data.city")city:String
    ): MyWeather
}
