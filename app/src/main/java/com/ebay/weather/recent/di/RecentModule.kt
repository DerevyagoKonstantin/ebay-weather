package com.ebay.weather.recent.di

import com.ebay.weather.recent.RecentViewModelFactory
import com.ebay.weather.recent.usecase.DeleteWeatherSearchUseCase
import com.ebay.weather.recent.usecase.GetAllWeatherSearchesUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val recentModule = Kodein.Module {
    bind<DeleteWeatherSearchUseCase>() with provider { DeleteWeatherSearchUseCase(instance()) }
    bind<GetAllWeatherSearchesUseCase>() with provider { GetAllWeatherSearchesUseCase(instance()) }
    bind<RecentViewModelFactory>() with provider { RecentViewModelFactory(instance(), instance()) }
}