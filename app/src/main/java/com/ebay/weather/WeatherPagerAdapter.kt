package com.ebay.weather

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ebay.weather.recent.RecentFragment
import com.ebay.weather.search.SearchFragment


class WeatherPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            1 -> RecentFragment()
            else -> throw IllegalArgumentException("")
        }
    }
}