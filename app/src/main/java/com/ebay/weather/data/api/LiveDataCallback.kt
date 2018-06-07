package com.ebay.weather.data.api

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LiveDataCallback<T> : LiveData<NetworkData<T>>, Callback<T> {

    constructor() {
        value = NetworkData()
    }

    constructor(call: Call<T>) {
        call.enqueue(this)
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        response?.let {
            value = if (it.isSuccessful) {
                NetworkData(data = it.body())
            } else {
                NetworkData(error = Throwable(it.errorBody()?.string()))
            }
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        value = NetworkData(error = t)
    }

}