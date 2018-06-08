package com.ebay.weather.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ebay.weather.data.db.entity.ID
import com.ebay.weather.data.db.entity.WEATHER_SEARCH
import com.ebay.weather.data.db.entity.WeatherSearch


@Dao
interface WeatherSearchDao {

    @Insert
    fun insert(weatherSearch: WeatherSearch): Long

    @Delete
    fun delete(weatherSearch: WeatherSearch)

    @Query("SELECT * FROM $WEATHER_SEARCH ORDER BY $ID DESC")
    fun getAll(): LiveData<List<WeatherSearch>>

}