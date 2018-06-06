package com.ebay.weather.search.di

import com.ebay.weather.data.api.di.weatherRemoteModule
import com.ebay.weather.search.SearchViewModelFactory
import com.ebay.weather.search.usecase.GetWeatherUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val searchModule = Kodein.Module {
    import(weatherRemoteModule)
    bind<GetWeatherUseCase>() with provider { GetWeatherUseCase(instance()) }
    bind<SearchViewModelFactory>() with provider { SearchViewModelFactory(instance()) }
}