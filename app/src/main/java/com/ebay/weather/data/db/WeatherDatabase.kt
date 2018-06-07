package com.ebay.weather.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ebay.weather.data.db.dao.WeatherSearchDao
import com.ebay.weather.data.db.entity.WeatherSearch
import kotlinx.coroutines.experimental.launch

const val WEATHER_DB_NAME = "weather-db-name"

@Database(entities = [(WeatherSearch::class)], version = 1)
abstract class WeatherDatabase : RoomDatabase(), WeatherLocalSource {
    abstract fun weatherSearchDao(): WeatherSearchDao

    override fun addWeatherSearch(searchText: String) {
        launch {
            weatherSearchDao().insert(WeatherSearch(searchText))
        }
    }

    override fun deleteWeatherSearch(weatherSearch: WeatherSearch) {
        launch {
            weatherSearchDao().delete(weatherSearch)
        }
    }

    override fun getAllWeatherSearches(): LiveData<List<WeatherSearch>> {
        return weatherSearchDao().getAll()
    }
}