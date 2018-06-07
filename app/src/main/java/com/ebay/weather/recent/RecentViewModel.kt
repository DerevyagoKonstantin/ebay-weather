package com.ebay.weather.recent

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.ebay.weather.data.db.entity.WeatherSearch
import com.ebay.weather.recent.usecase.DeleteWeatherSearchUseCase
import com.ebay.weather.recent.usecase.GetAllWeatherSearchesUseCase


class RecentViewModel(
        private val deleteWeatherSearchUseCase: DeleteWeatherSearchUseCase,
        getAllWeatherSearchesUseCase: GetAllWeatherSearchesUseCase
) : ViewModel() {
    val weatherSearches = getAllWeatherSearchesUseCase.execute(Unit)

    val searchesVisibility: LiveData<Boolean> = Transformations.map(weatherSearches, { it.isNotEmpty() })
    val emptyVisibility: LiveData<Boolean> = Transformations.map(searchesVisibility, { !it })

    val selectedSearch = MutableLiveData<String>()

    fun deleteSearch(weatherSearch: WeatherSearch) {
        deleteWeatherSearchUseCase.execute(weatherSearch)
    }
}