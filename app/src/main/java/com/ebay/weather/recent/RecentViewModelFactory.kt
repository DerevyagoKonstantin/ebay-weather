package com.ebay.weather.recent

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ebay.weather.recent.usecase.DeleteWeatherSearchUseCase
import com.ebay.weather.recent.usecase.GetAllWeatherSearchesUseCase


class RecentViewModelFactory(
        private val deleteWeatherSearchUseCase: DeleteWeatherSearchUseCase,
        private val getAllWeatherSearchesUseCase: GetAllWeatherSearchesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = RecentViewModel(
            deleteWeatherSearchUseCase,
            getAllWeatherSearchesUseCase
    ) as T
}