package com.ebay.weather.search.usecase

import com.ebay.weather.base.UseCase
import com.ebay.weather.data.api.LiveDataCallback
import com.ebay.weather.data.api.WeatherRemoteSource
import com.ebay.weather.entity.WeatherInfo


class SearchWeatherUseCase(
        private val weatherRemoteSource: WeatherRemoteSource
) : UseCase<String, LiveDataCallback<WeatherInfo>> {

    override fun execute(input: String): LiveDataCallback<WeatherInfo> {
        return if (input.isNotEmpty()) {
            weatherRemoteSource.getWeather(input)
        } else {
            LiveDataCallback()
        }
    }
}