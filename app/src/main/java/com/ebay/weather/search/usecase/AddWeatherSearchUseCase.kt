package com.ebay.weather.search.usecase

import com.ebay.weather.base.UseCase
import com.ebay.weather.data.db.WeatherLocalSource


class AddWeatherSearchUseCase(
        private val weatherLocalSource: WeatherLocalSource
) : UseCase<String, Unit> {
    override fun execute(input: String) {
        weatherLocalSource.addWeatherSearch(input)
    }
}