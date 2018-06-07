package com.ebay.weather.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ebay.weather.search.usecase.AddWeatherSearchUseCase
import com.ebay.weather.search.usecase.GetRecentWeatherSearchUseCase
import com.ebay.weather.search.usecase.SaveRecentWeatherSearchUseCase
import com.ebay.weather.search.usecase.SearchWeatherUseCase


class SearchViewModelFactory(
        private val searchWeatherUseCase: SearchWeatherUseCase,
        private val saveRecentWeatherSearchUseCase: SaveRecentWeatherSearchUseCase,
        private val getRecentWeatherSearchUseCase: GetRecentWeatherSearchUseCase,
        private val addWeatherSearchUseCase: AddWeatherSearchUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel(
            searchWeatherUseCase,
            saveRecentWeatherSearchUseCase,
            getRecentWeatherSearchUseCase,
            addWeatherSearchUseCase
    ) as T
}