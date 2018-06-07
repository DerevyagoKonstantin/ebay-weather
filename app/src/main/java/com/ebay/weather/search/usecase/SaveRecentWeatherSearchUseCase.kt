package com.ebay.weather.search.usecase

import com.ebay.weather.base.UseCase
import com.ebay.weather.data.preferences.WeatherPreferences


class SaveRecentWeatherSearchUseCase(
        private val weatherPreferences: WeatherPreferences
) : UseCase<String, Unit> {
    override fun execute(input: String) {
        weatherPreferences.saveRecentWeatherSearch(input)
    }
}