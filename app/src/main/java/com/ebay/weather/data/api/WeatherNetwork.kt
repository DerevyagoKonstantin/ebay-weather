package com.ebay.weather.data.api

import com.ebay.weather.entity.WeatherInfo
import java.util.regex.Pattern


const val WEATHER_API_KEY = "95d190a434083879a6398aafd54d9e73"

class WeatherNetwork(private val api: WeatherApi) : WeatherRemoteSource {

    private val zipcodeRegex = "^\\d{5}(-\\d{4})?$"

    override fun getWeather(search: String): LiveDataCallback<WeatherInfo> {
        return if (Pattern.matches(zipcodeRegex, search)) {
            getWeatherByZipcode(search)
        } else {
            getWeatherByCity(search)
        }
    }

    private fun getWeatherByZipcode(zipcode: String): LiveDataCallback<WeatherInfo> {
        val call = api.getWeatherByZipcode(zipcode, WEATHER_API_KEY)
        return LiveDataCallback(call)
    }

    private fun getWeatherByCity(city: String): LiveDataCallback<WeatherInfo> {
        val call = api.getWeatherByCity(city, WEATHER_API_KEY)
        return LiveDataCallback(call)
    }
}