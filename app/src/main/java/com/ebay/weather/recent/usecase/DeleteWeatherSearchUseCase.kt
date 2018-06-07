package com.ebay.weather.recent.usecase

import com.ebay.weather.base.UseCase
import com.ebay.weather.data.db.WeatherLocalSource
import com.ebay.weather.data.db.entity.WeatherSearch


class DeleteWeatherSearchUseCase(
        private val weatherLocalSource: WeatherLocalSource
) : UseCase<WeatherSearch, Unit> {
    override fun execute(input: WeatherSearch) {
        weatherLocalSource.deleteWeatherSearch(input)
    }
}