package com.rakangsoftware.weatherapp.di

import com.rakangsoftware.weatherapp.BuildConfig
import com.rakangsoftware.weatherapp.data.remote.repository.WeatherRepositoryApi
import com.rakangsoftware.weatherapp.domain.weather.repository.WeatherRepository
import com.rakangsoftware.weatherapp.domain.weather.usecases.GetWeather
import com.rakangsoftware.weatherapp.screen.weather.WeatherUseCases
import com.rakangsoftware.weatherapp.screen.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { BuildConfig.WEATHER_API_KEY }

    single<WeatherRepository> { WeatherRepositoryApi(apiKey = get()) }

    factory { GetWeather(weatherRepository = get()) }

    factory { WeatherUseCases(getWeather = get()) }
    viewModel { WeatherViewModel(useCases = get()) }
}
