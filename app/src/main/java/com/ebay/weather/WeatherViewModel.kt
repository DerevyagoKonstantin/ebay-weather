package com.ebay.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class WeatherViewModel : ViewModel() {
    val selectedSearch = MutableLiveData<String>()
}