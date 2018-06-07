package com.ebay.weather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.ebay.weather.base.BaseActivity
import com.ebay.weather.di.weatherModule
import com.ebay.weather.extensions.hideKeyboard
import com.ebay.weather.extensions.showKeyboard
import kotlinx.android.synthetic.main.activity_weather.weatherBottomNavigation
import kotlinx.android.synthetic.main.activity_weather.weatherViewPager

class WeatherActivity : BaseActivity() {
    override val viewId = R.layout.activity_weather

    override fun provideInjections() = weatherModule

    override fun init() {
        weatherViewPager.adapter = WeatherPagerAdapter(supportFragmentManager)

        weatherBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.weatherNavigationSearch -> openSearch()
                R.id.weatherNavigationRecent -> openRecent()
            }
            true
        }
    }

    override fun bind() {
        val viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        viewModel.selectedSearch.observe(this, Observer {
            weatherBottomNavigation.selectedItemId = R.id.weatherNavigationSearch
        })
    }

    private fun openSearch() {
        weatherViewPager.currentItem = 0
        showKeyboard()
    }

    private fun openRecent() {
        weatherViewPager.currentItem = 1
        hideKeyboard()
    }

}
