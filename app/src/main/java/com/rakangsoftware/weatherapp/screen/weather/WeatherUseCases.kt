package com.rakangsoftware.weatherapp.screen.weather

import com.rakangsoftware.weatherapp.domain.weather.usecases.GetWeather

data class WeatherUseCases(
    val getWeather: GetWeather,
)
