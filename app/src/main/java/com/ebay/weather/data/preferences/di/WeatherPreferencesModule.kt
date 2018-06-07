package com.ebay.weather.data.preferences.di

import com.ebay.weather.data.preferences.WeatherPreferences
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


val weatherPreferencesModule = Kodein.Module {
    bind<WeatherPreferences>() with singleton { WeatherPreferences(instance(), instance()) }
}