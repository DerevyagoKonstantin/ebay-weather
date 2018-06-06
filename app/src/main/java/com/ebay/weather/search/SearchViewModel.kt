package com.ebay.weather.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.ebay.weather.data.api.LiveDataCallback
import com.ebay.weather.entity.WeatherInfo
import com.ebay.weather.search.usecase.GetWeatherUseCase
import java.lang.Exception


class SearchViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {
    private val weatherResponse = MutableLiveData<LiveDataCallback<WeatherInfo>>()
    val weatherInfo: LiveData<WeatherInfo> = Transformations.switchMap(weatherResponse, { it.data })
    val weatherError: LiveData<Exception> = Transformations.switchMap(weatherResponse, { it.error })

    val weatherVisibility: LiveData<Boolean> = MediatorLiveData<Boolean>()
    val emptyVisibility: LiveData<Boolean> = Transformations.map(weatherVisibility, { !it })
    val progressVisibility: LiveData<Boolean> = MediatorLiveData<Boolean>()

    val emptySearch: LiveData<Boolean> = MutableLiveData<Boolean>()

    init {
        (weatherVisibility as MediatorLiveData).addSource(weatherInfo, { weatherVisibility.value = true })
        weatherVisibility.addSource(weatherError, { weatherVisibility.value = false })
        weatherVisibility.addSource(emptySearch, { it?.let { if (it) weatherVisibility.value = false } })

        (progressVisibility as MediatorLiveData).addSource(weatherInfo, { progressVisibility.value = false })
        progressVisibility.addSource(weatherError, { progressVisibility.value = false })
    }

    fun searchWeather(searchText: String) {
        val isEmpty = searchText.isEmpty()
        (emptySearch as MutableLiveData).value = isEmpty
        if (!isEmpty) {
            (progressVisibility as MutableLiveData).value = true
            weatherResponse.value = getWeatherUseCase.execute(searchText)
        }
    }
}