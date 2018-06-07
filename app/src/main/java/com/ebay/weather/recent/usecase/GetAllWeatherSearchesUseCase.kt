package com.ebay.weather.recent.usecase

import android.arch.lifecycle.LiveData
import com.ebay.weather.base.UseCase
import com.ebay.weather.data.db.WeatherLocalSource
import com.ebay.weather.data.db.entity.WeatherSearch


class GetAllWeatherSearchesUseCase(
        private val weatherLocalSource: WeatherLocalSource
) : UseCase<Unit, LiveData<List<WeatherSearch>>> {
    override fun execute(input: Unit): LiveData<List<WeatherSearch>> {
        return weatherLocalSource.getAllWeatherSearches()
    }
}