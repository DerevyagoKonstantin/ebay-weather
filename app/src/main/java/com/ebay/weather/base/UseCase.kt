package com.ebay.weather.base


interface UseCase<in T, out U> {
    fun execute(input: T): U
}