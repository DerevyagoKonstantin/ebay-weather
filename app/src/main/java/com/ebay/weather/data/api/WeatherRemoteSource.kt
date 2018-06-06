package com.ebay.weather.data.api

import com.ebay.weather.entity.WeatherInfo


interface WeatherRemoteSource {

    fun getWeather(search: String): LiveDataCallback<WeatherInfo>
}