package com.ebay.weather.data.api

import com.ebay.weather.entity.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

interface WeatherApi {

    @GET("weather")
    fun getWeatherByCity(@Query("q") city: String, @Query("appid") apiKey: String): Call<WeatherInfo>

    @GET("weather")
    fun getWeatherByZipcode(@Query("zip") zip: String, @Query("appid") apiKey: String): Call<WeatherInfo>
}