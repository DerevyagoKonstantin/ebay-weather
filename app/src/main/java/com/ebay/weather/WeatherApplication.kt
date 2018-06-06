package com.ebay.weather

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule


class WeatherApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@WeatherApplication))
    }
}