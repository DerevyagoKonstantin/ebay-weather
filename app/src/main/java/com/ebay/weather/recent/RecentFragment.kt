package com.ebay.weather.recent

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.ebay.weather.R
import com.ebay.weather.WeatherViewModel
import com.ebay.weather.base.BaseFragment
import com.ebay.weather.extensions.visible
import com.ebay.weather.recent.adapter.SearchesAdapter
import com.ebay.weather.recent.di.recentModule
import kotlinx.android.synthetic.main.fragment_recent.view.recentEmptyView
import kotlinx.android.synthetic.main.fragment_recent.view.recentRecyclerView
import org.kodein.di.generic.instance


class RecentFragment : BaseFragment() {

    private val adapter = SearchesAdapter()

    override val viewId = R.layout.fragment_recent

    override fun provideInjections() = recentModule

    override fun init(view: View) {
        view.recentRecyclerView.adapter = adapter
    }

    override fun bind(view: View) {
        val viewModelFactory by instance<RecentViewModelFactory>()
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecentViewModel::class.java)

        adapter.itemClick.observe(this, Observer { it?.let { viewModel.selectedSearch.value = it } })
        adapter.deleteClick.observe(this, Observer { it?.let { viewModel.deleteSearch(it) } })

        viewModel.weatherSearches.observe(this, Observer { it?.let { adapter.searches = it } })

        viewModel.searchesVisibility.observe(this, Observer {
            it?.let { visible -> view.recentRecyclerView.visible = visible }
        })
        viewModel.emptyVisibility.observe(this, Observer {
            it?.let { visible -> view.recentEmptyView.visible = visible }
        })

        activity?.let {
            val parentViewModel = ViewModelProviders.of(it).get(WeatherViewModel::class.java)
            viewModel.selectedSearch.observe(this, Observer { parentViewModel.selectedSearch.value = it })
        }
    }
}