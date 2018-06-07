package com.ebay.weather.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.ebay.weather.data.api.NetworkData
import com.ebay.weather.entity.Weather
import com.ebay.weather.search.usecase.AddWeatherSearchUseCase
import com.ebay.weather.search.usecase.GetRecentWeatherSearchUseCase
import com.ebay.weather.search.usecase.SaveRecentWeatherSearchUseCase
import com.ebay.weather.search.usecase.SearchWeatherUseCase


class SearchViewModel(
        private val searchWeatherUseCase: SearchWeatherUseCase,
        private val saveRecentWeatherSearchUseCase: SaveRecentWeatherSearchUseCase,
        getRecentWeatherSearchUseCase: GetRecentWeatherSearchUseCase,
        private val addWeatherSearchUseCase: AddWeatherSearchUseCase
) : ViewModel() {
    val recentSearchInfo = getRecentWeatherSearchUseCase.execute(Unit)

    val searchText = MutableLiveData<String>()
    private val weatherResponse: LiveData<NetworkData<Weather>> = Transformations.switchMap(searchText, {
        saveRecentWeatherSearchUseCase.execute(it)
        searchWeatherUseCase.execute(it)
    })
    val weatherInfo: LiveData<Weather?> = Transformations.map(weatherResponse, { it.data.also { saveSearch(it) } })
    val weatherError: LiveData<Throwable?> = Transformations.map(weatherResponse, { it.error })

    val weatherVisibility: LiveData<Boolean> = Transformations.map(weatherInfo, { it != null })
    val emptyVisibility: LiveData<Boolean> = Transformations.map(weatherVisibility, { !it })
    val progressVisibility: LiveData<Boolean> = MediatorLiveData<Boolean>()

    init {
        (progressVisibility as MediatorLiveData).addSource(searchText, { progressVisibility.value = true })
        progressVisibility.addSource(weatherResponse, { progressVisibility.value = false })
    }

    private fun saveSearch(data: Weather?) {
        val search = searchText.value
        if (data != null && search != null) {
            addWeatherSearchUseCase.execute(search)
        }
    }

}