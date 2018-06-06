package com.ebay.weather.data.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class LiveDataCallback<T>(call: Call<T>) : Callback<T> {

    val data: LiveData<T> = MutableLiveData<T>()
    val error: LiveData<Exception> = MutableLiveData<Exception>()

    init {
        call.enqueue(this)
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        response?.let {
            if (it.isSuccessful) {
                (data as MutableLiveData).value = it.body()
            } else {
                (error as MutableLiveData).value = Exception(it.errorBody().toString())
            }
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        (error as MutableLiveData).value = Exception(t)
    }
}