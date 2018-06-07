package com.ebay.weather.recent.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebay.weather.R
import com.ebay.weather.data.db.entity.WeatherSearch
import kotlinx.android.synthetic.main.search_item.view.searchDeleteView
import kotlinx.android.synthetic.main.search_item.view.searchTextView


class SearchesAdapter : RecyclerView.Adapter<SearchesAdapter.SearchViewHolder>() {

    var searches: List<WeatherSearch> = listOf()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun getOldListSize() = field.size

                override fun getNewListSize() = value.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return field[oldItemPosition].id == value[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return field[oldItemPosition] == value[newItemPosition]
                }
            })
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var itemClick = MutableLiveData<String>()
    var deleteClick = MutableLiveData<WeatherSearch>()

    override fun getItemCount() = searches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searches[position], itemClick, deleteClick)
    }

    class SearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherSearch: WeatherSearch, itemClick: MutableLiveData<String>, deleteClick: MutableLiveData<WeatherSearch>) {
            itemView.searchTextView.text = weatherSearch.searchText

            itemView.setOnClickListener {
                itemClick.value = weatherSearch.searchText
            }

            itemView.searchDeleteView.setOnClickListener {
                deleteClick.value = weatherSearch
            }
        }
    }
}