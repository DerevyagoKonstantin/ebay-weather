package com.ebay.weather

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ebay.weather.data.db.WeatherDatabase
import com.ebay.weather.data.db.dao.WeatherSearchDao
import com.ebay.weather.data.db.entity.WeatherSearch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var weatherSearchDao: WeatherSearchDao

    @Before
    fun create() {
        val context = InstrumentationRegistry.getTargetContext()
        weatherDatabase = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java).build()
        weatherSearchDao = weatherDatabase.weatherSearchDao()
    }

    @After
    fun destroy() {
        weatherDatabase.close()
    }

    @Test
    fun insert() {
        val weatherSearch = WeatherSearch("Amsterdam")
        val id = weatherSearchDao.insert(weatherSearch)
        weatherSearch.id = id

        val searches = weatherSearchDao.getAll()
        searches.observeForever {}
        Thread.sleep(1000)
        val search = searches.value!![0]
        assertEquals(weatherSearch, search)
    }

    @Test
    fun delete() {
        val searchTexts = listOf("Amsterdam", "Minsk", "New-York", "London")
        val weatherSearches = searchTexts.map {
            val weatherSearch = WeatherSearch(it)
            val id = weatherSearchDao.insert(weatherSearch)
            weatherSearch.id = id
            weatherSearch
        }.toMutableList()

        val weatherSearch = weatherSearches[0]
        weatherSearchDao.delete(weatherSearch)
        weatherSearches.removeAt(0)
        weatherSearches.reverse()

        val searches = weatherSearchDao.getAll()
        searches.observeForever {}
        Thread.sleep(1000)
        assertEquals(weatherSearches, searches.value)
    }

}
