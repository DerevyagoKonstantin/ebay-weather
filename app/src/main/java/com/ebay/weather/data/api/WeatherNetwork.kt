package com.ebay.weather.data.api

import com.ebay.weather.entity.WeatherInfo
import java.util.regex.Pattern


const val WEATHER_API_KEY = "95d190a434083879a6398aafd54d9e73"
const val ZIPCODE_REGEX = "^\\d{5}(-\\d{4})?\$"
const val GPS_REGEX = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)\\s*,\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)\$"

class WeatherNetwork(private val api: WeatherApi) : WeatherRemoteSource {

    override fun getWeather(search: String): LiveDataCallback<WeatherInfo> {
        return when {
            Pattern.matches(ZIPCODE_REGEX, search) -> getWeatherByZipcode(search)
            Pattern.matches(GPS_REGEX, search) -> getWeatherByGps(search)
            else -> getWeatherByCity(search)
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

    private fun getWeatherByGps(gps: String): LiveDataCallback<WeatherInfo> {
        val lat = gps.substringBefore(",").trim()
        val lon = gps.substringAfter(",").trim()
        val call = api.getWeatherByGps(lat, lon, WEATHER_API_KEY)
        return LiveDataCallback(call)
    }
}