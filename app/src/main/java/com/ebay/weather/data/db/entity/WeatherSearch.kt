package com.ebay.weather.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


const val WEATHER_SEARCH = "weather_search"
const val SEARCH_TEXT = "search_text"
const val ID = "id"

@Entity(tableName = WEATHER_SEARCH)
data class WeatherSearch(
        @ColumnInfo(name = SEARCH_TEXT)
        var searchText: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Long = 0
}