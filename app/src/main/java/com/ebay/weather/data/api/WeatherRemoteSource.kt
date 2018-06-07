package com.ebay.weather.data.api

import android.arch.lifecycle.LiveData
import com.ebay.weather.entity.Weather


interface WeatherRemoteSource {

    fun getWeather(search: String): LiveData<NetworkData<Weather>>
}