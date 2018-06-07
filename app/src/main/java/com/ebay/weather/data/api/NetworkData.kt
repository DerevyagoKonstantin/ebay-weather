package com.ebay.weather.data.api


data class NetworkData<out T>(val data: T? = null, val error: Throwable? = null)