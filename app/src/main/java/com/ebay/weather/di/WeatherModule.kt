package com.ebay.weather.di

import com.ebay.weather.data.db.di.weatherLocalModule
import org.kodein.di.Kodein


val weatherModule = Kodein.Module {
    import(weatherLocalModule)
}