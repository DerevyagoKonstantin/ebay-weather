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
import kotlinx.android.synthetic.main.weather_item.view.weatherWindTextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


class WeatherViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private val decimalFormat: DecimalFormat

    init {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.decimalSeparator = '.'
        decimalFormat = DecimalFormat("0.##", decimalFormatSymbols)
    }

    fun bind(weatherInfo: WeatherInfo) {
        val context = itemView.context

        val coordinates = weatherInfo.coord
        val lat = decimalFormat.format(coordinates.lat)
        val lon = decimalFormat.format(coordinates.lon)
        val location = "${weatherInfo.name}, ${weatherInfo.sys.country}    ($lat°, $lon°)"
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

        val pressureValue = decimalFormat.format(weatherInfo.main.pressure)
        val pressure = context.getString(R.string.weather_atmospheric_pressure_value, pressureValue)
        itemView.weatherAtmosphericPressureTextView.text = pressure

        val windSpeed = decimalFormat.format(weatherInfo.wind.speed)
        val wind = context.getString(R.string.weather_wind_value, windSpeed)
        itemView.weatherWindTextView.text = wind

        val clouds = "${weatherInfo.clouds.all}%"
        itemView.weatherCloudsTextView.text = clouds
    }
}