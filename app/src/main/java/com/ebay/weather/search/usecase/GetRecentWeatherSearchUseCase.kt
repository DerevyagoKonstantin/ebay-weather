package com.ebay.weather.search.usecase

import com.ebay.weather.base.UseCase
import com.ebay.weather.data.preferences.WeatherPreferences


class GetRecentWeatherSearchUseCase(
        private val weatherPreferences: WeatherPreferences
) : UseCase<Unit, String> {
    override fun execute(input: Unit) = weatherPreferences.getRecentWeatherSearch()
}