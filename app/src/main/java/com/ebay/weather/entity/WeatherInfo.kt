package com.ebay.weather.entity


data class WeatherInfo(
        val coord: Coordinates = Coordinates(),
        val weather: List<Weather> = listOf(),
        val base: String = "",
        val main: Main = Main(),
        val wind: Wind = Wind(),
        val clouds: Clouds = Clouds(),
        val dt: Long = 0,
        val sys: Sys = Sys(),
        val id: Long = 0,
        val name: String = "",
        val cod: Int = 0
)

data class Coordinates(
        val lon: Float = 0.0f,
        val lat: Float = 0.0f
)

data class Weather(
        val id: Long = 0,
        val main: String = "",
        val description: String = "",
        val icon: String = ""
)

data class Main(
        val temp: Float = 0.0f,
        val pressure: Float = 0.0f,
        val humidity: Float = 0.0f,
        val tempMin: Float = 0.0f,
        val tempMax: Float = 0.0f
)

data class Wind(
        val speed: Float = 0.0f,
        val deg: Float = 0.0f
)

data class Clouds(val all: Int = 0)

data class Sys(
        val type: Int = 0,
        val id: Long = 0,
        val message: Float = 0.0f,
        val country: String = "",
        val sunrise: Long = 0,
        val sunset: Long = 0
)