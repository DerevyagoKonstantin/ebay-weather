package com.ebay.weather.search.usecase

import android.arch.lifecycle.LiveData
import com.ebay.weather.base.UseCase
import com.ebay.weather.data.api.LiveDataCallback
import com.ebay.weather.data.api.NetworkData
import com.ebay.weather.data.api.WeatherRemoteSource
import com.ebay.weather.entity.Weather


class SearchWeatherUseCase(
        private val weatherRemoteSource: WeatherRemoteSource
) : UseCase<String, LiveData<NetworkData<Weather>>> {

    override fun execute(input: String): LiveData<NetworkData<Weather>> {
        return if (input.isNotEmpty()) {
            weatherRemoteSource.getWeather(input)
        } else {
            LiveDataCallback()
        }
    }
}