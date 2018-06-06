package com.ebay.weather.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ebay.weather.search.usecase.GetWeatherUseCase


class SearchViewModelFactory(private val getWeatherUseCase: GetWeatherUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel(getWeatherUseCase) as T
}