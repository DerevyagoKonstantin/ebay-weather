package com.ebay.weather.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ebay.weather.R
import com.ebay.weather.entity.Weather
import kotlinx.android.synthetic.main.view_weather.view.weatherAtmosphericPressureTextView
import kotlinx.android.synthetic.main.view_weather.view.weatherCloudsTextView
import kotlinx.android.synthetic.main.view_weather.view.weatherDescriptionTextView
import kotlinx.android.synthetic.main.view_weather.view.weatherLocationTextView
import kotlinx.android.synthetic.main.view_weather.view.weatherTemperatureTextView
import kotlinx.android.synthetic.main.view_weather.view.weatherTitleTextView
import kotlinx.android.synthetic.main.view_weather.view.weatherWindTextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


class WeatherView : LinearLayout {

    private val decimalFormat: DecimalFormat

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        inflate(context, R.layout.view_weather, this)
        orientation = VERTICAL

        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.decimalSeparator = '.'
        decimalFormat = DecimalFormat("0.##", decimalFormatSymbols)
    }

    fun bind(weather: Weather) {
        val lat = decimalFormat.format(weather.lat)
        val lon = decimalFormat.format(weather.lon)
        val location = "${weather.city}, ${weather.country}    ($lat°, $lon°)"
        weatherLocationTextView.text = location

        weatherTitleTextView.text = weather.title
        weatherDescriptionTextView.text = weather.description

        val temperature = "${weather.temperature} K"
        weatherTemperatureTextView.text = temperature

        val pressureValue = decimalFormat.format(weather.pressure)
        val pressure = context.getString(R.string.weather_atmospheric_pressure_value, pressureValue)
        weatherAtmosphericPressureTextView.text = pressure

        val windSpeed = decimalFormat.format(weather.wind)
        val wind = context.getString(R.string.weather_wind_value, windSpeed)
        weatherWindTextView.text = wind

        val clouds = "${weather.clouds}%"
        weatherCloudsTextView.text = clouds
    }
}