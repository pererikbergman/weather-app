package com.rakangsoftware.weatherapp.di

import com.rakangsoftware.weatherapp.data.remote.repository.WeatherRepositoryApi
import com.rakangsoftware.weatherapp.domain.weather.repository.WeatherRepository
import com.rakangsoftware.weatherapp.domain.weather.usecases.CheckMinNumOfChar
import com.rakangsoftware.weatherapp.domain.weather.usecases.GetWeather
import com.rakangsoftware.weatherapp.screen.weather.WeatherUseCases
import com.rakangsoftware.weatherapp.screen.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideWeatherApiKey() }

    single<WeatherRepository> { WeatherRepositoryApi(apiKey = get()) }

    factory { GetWeather(weatherRepository = get()) }
    factory { CheckMinNumOfChar() }

    factory { WeatherUseCases(getWeather = get(), checkMinNumOfChar = get()) }
    viewModel { WeatherViewModel(useCases = get()) }
}

fun provideWeatherApiKey(): String {
    return "put-your-key-here"
}
