package com.ebay.weather.data.db.di

import android.arch.persistence.room.Room
import com.ebay.weather.data.db.WEATHER_DB_NAME
import com.ebay.weather.data.db.WeatherDatabase
import com.ebay.weather.data.db.WeatherLocalSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


val weatherLocalModule = Kodein.Module {
    bind<WeatherLocalSource>() with singleton {
        Room.databaseBuilder(instance(), WeatherDatabase::class.java, WEATHER_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}