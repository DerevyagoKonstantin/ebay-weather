package com.ebay.weather.data.api.di

import com.ebay.weather.data.api.BASE_URL
import com.ebay.weather.data.api.WeatherApi
import com.ebay.weather.data.api.WeatherNetwork
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val TIMEOUT = 30L

val weatherRemoteModule = Kodein.Module {
    bind<Gson>() with singleton {
        GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    bind<WeatherApi>() with singleton {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(instance()))
                .client(instance())
                .build()
                .create(WeatherApi::class.java)
    }

    bind<WeatherNetwork>() with singleton { WeatherNetwork(instance()) }
}