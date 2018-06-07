package com.ebay.weather.data.db

import android.arch.lifecycle.LiveData
import com.ebay.weather.data.db.entity.WeatherSearch


interface WeatherLocalSource {
    fun addWeatherSearch(searchText: String)
    fun deleteWeatherSearch(weatherSearch: WeatherSearch)
    fun getAllWeatherSearches(): LiveData<List<WeatherSearch>>
}