package com.ebay.weather.entity


data class Weather(
        val id: Long = 0,
        val city: String = "",
        val country: String = "",
        val lat: Float = 0.0f,
        val lon: Float = 0.0f,
        val title: String = "",
        val description: String = "",
        val temperature: Float = 0.0f,
        val pressure: Float = 0.0f,
        val wind: Float = 0.0f,
        val clouds: Int = 0
)