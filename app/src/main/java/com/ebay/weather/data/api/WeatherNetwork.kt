package com.ebay.weather.data.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.ebay.weather.data.api.entity.WeatherInfo
import com.ebay.weather.entity.Weather
import java.util.regex.Pattern


const val WEATHER_API_KEY = "95d190a434083879a6398aafd54d9e73"
const val ZIPCODE_REGEX = "^\\d{5}(-\\d{4})?\$"
const val GPS_REGEX = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)\\s*,\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)\$"

class WeatherNetwork(private val api: WeatherApi) : WeatherRemoteSource {

    override fun getWeather(search: String): LiveData<NetworkData<Weather>> {
        val result = when {
            Pattern.matches(ZIPCODE_REGEX, search) -> getWeatherByZipcode(search)
            Pattern.matches(GPS_REGEX, search) -> getWeatherByGps(search)
            else -> getWeatherByCity(search)
        }
        return Transformations.map(result, { NetworkData(it.data.parse(), it.error) })
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

private fun WeatherInfo?.parse(): Weather? {
    return if (this != null) {
        val title = if (weather.isNotEmpty()) {
            weather[0].main
        } else {
            ""
        }
        val description = if (weather.isNotEmpty()) {
            weather[0].description
        } else {
            ""
        }

        Weather(
                id,
                name,
                sys.country,
                coord.lat,
                coord.lon,
                title,
                description,
                main.temp,
                main.pressure,
                wind.speed,
                clouds.all
        )
    } else {
        null
    }
}