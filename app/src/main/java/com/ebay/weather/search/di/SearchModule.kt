package com.ebay.weather.search.di

import com.ebay.weather.data.api.di.weatherRemoteModule
import com.ebay.weather.data.preferences.di.weatherPreferencesModule
import com.ebay.weather.di.gsonModule
import com.ebay.weather.search.SearchViewModelFactory
import com.ebay.weather.search.usecase.GetRecentWeatherSearchUseCase
import com.ebay.weather.search.usecase.SaveRecentWeatherSearchUseCase
import com.ebay.weather.search.usecase.SearchWeatherUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val searchModule = Kodein.Module {
    import(gsonModule)
    import(weatherRemoteModule)
    import(weatherPreferencesModule)
    bind<SearchWeatherUseCase>() with provider { SearchWeatherUseCase(instance()) }
    bind<SaveRecentWeatherSearchUseCase>() with provider { SaveRecentWeatherSearchUseCase(instance()) }
    bind<GetRecentWeatherSearchUseCase>() with provider { GetRecentWeatherSearchUseCase(instance()) }
    bind<SearchViewModelFactory>() with provider { SearchViewModelFactory(instance(), instance(), instance()) }
}