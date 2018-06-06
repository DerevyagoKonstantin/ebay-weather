package com.ebay.weather.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.ebay.weather.R
import com.ebay.weather.entity.WeatherInfo
import com.ebay.weather.extensions.visible
import kotlinx.android.synthetic.main.weather_item.view.weatherAtmosphericPressureTextView
import kotlinx.android.synthetic.main.weather_item.view.weatherCloudsTextView
import kotlinx.android.synthetic.main.weather_item.view.weatherDescriptionTextView
import kotlinx.android.synthetic.main.weather_item.view.weatherLocationTextView
import kotlinx.android.synthetic.main.weather_item.view.weatherTemperatureTextView
import kotlinx.android.synthetic.main.weather_item.view.weatherTitleTextView


class WeatherViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(weatherInfo: WeatherInfo) {
        val context = itemView.context

        val location = "${weatherInfo.name}, ${weatherInfo.sys.country}"
        itemView.weatherLocationTextView.text = location

        val isWeatherNotEmpty = weatherInfo.weather.isNotEmpty()
        itemView.weatherTitleTextView.visible = isWeatherNotEmpty
        itemView.weatherDescriptionTextView.visible = isWeatherNotEmpty
        if (isWeatherNotEmpty) {
            val weather = weatherInfo.weather[0]
            itemView.weatherTitleTextView.text = weather.main
            itemView.weatherDescriptionTextView.text = weather.description
        }

        val temperature = "${weatherInfo.main.temp} K"
        itemView.weatherTemperatureTextView.text = temperature
        val pressure = context.getString(R.string.weather_atmospheric_pressure_value, weatherInfo.main.pressure)
        itemView.weatherAtmosphericPressureTextView.text = pressure
        val clouds = "${weatherInfo.clouds.all}%"
        itemView.weatherCloudsTextView.text = clouds
    }
}