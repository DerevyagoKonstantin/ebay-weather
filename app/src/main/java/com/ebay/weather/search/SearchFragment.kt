package com.ebay.weather.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.ebay.weather.R
import com.ebay.weather.base.BaseFragment
import com.ebay.weather.entity.WeatherInfo
import com.ebay.weather.extensions.visible
import com.ebay.weather.search.adapter.WeatherViewHolder
import com.ebay.weather.search.di.searchModule
import kotlinx.android.synthetic.main.fragment_search.view.searchEditText
import kotlinx.android.synthetic.main.fragment_search.view.searchEmptyView
import kotlinx.android.synthetic.main.fragment_search.view.searchSwipeRefresh
import kotlinx.android.synthetic.main.fragment_search.view.searchWeatherView
import org.kodein.di.generic.instance


class SearchFragment : BaseFragment() {

    private lateinit var weatherViewHolder: WeatherViewHolder

    override val viewId = R.layout.fragment_search

    override fun provideInjections() = searchModule

    override fun init(view: View) {
        weatherViewHolder = WeatherViewHolder(view.searchWeatherView)
        view.searchSwipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        view.searchSwipeRefresh.isEnabled = false
    }

    override fun bind(view: View) {
        val viewModelFactory by instance<SearchViewModelFactory>()
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        view.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchWeather(s.toString())
                view.searchSwipeRefresh.isEnabled = !s.isNullOrEmpty()
            }
        })
        view.searchSwipeRefresh.setOnRefreshListener {
            viewModel.searchWeather(view.searchEditText.text.toString())
        }

        viewModel.weatherInfo.observe(this, Observer<WeatherInfo> {
            it?.let { weather -> weatherViewHolder.bind(weather) }
        })
        viewModel.weatherError.observe(this, Observer<Exception> {
            it?.let { exception -> showError(exception) }
        })
        viewModel.emptySearch.observe(this, Observer<Boolean> {
            it?.let { empty ->
                if (empty) {
                    showEmpty()
                }
            }
        })
        viewModel.weatherVisibility.observe(this, Observer<Boolean> {
            it?.let { visible -> view.searchWeatherView.visible = visible }
        })
        viewModel.emptyVisibility.observe(this, Observer<Boolean> {
            it?.let { visible -> view.searchEmptyView.visible = visible }
        })
        viewModel.progressVisibility.observe(this, Observer<Boolean> {
            it?.let { visible -> view.searchSwipeRefresh.isRefreshing = visible }
        })
    }

    private fun showEmpty() {
        bindEmptyView(R.string.weather_search_empty, R.drawable.ic_weather_empty)
    }

    private fun showError(exception: Exception) {
        exception.printStackTrace()
        bindEmptyView(R.string.weather_search_error, R.drawable.ic_weather_error)
    }

    private fun bindEmptyView(textRes: Int, imageRes: Int) {
        view?.searchEmptyView?.setText(textRes)
        view?.searchEmptyView?.setCompoundDrawablesWithIntrinsicBounds(0, imageRes, 0, 0)
    }
}