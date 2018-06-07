package com.ebay.weather.data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson


const val RECENT_WEATHER_SEARCH = "recent_weather_search"

class WeatherPreferences(private val preferences: SharedPreferences, private val gson: Gson) {

    fun saveRecentWeatherSearch(search: String) {
        preferences.edit().apply {
            putString(RECENT_WEATHER_SEARCH, search)
        }.apply()
    }

    fun getRecentWeatherSearch(): String = preferences.getString(RECENT_WEATHER_SEARCH, "")
}