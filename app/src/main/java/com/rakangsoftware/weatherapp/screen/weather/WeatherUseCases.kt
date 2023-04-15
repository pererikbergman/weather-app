package com.rakangsoftware.weatherapp.screen.weather

import com.rakangsoftware.weatherapp.domain.weather.usecases.CheckMinNumOfChar
import com.rakangsoftware.weatherapp.domain.weather.usecases.GetWeather

data class WeatherUseCases(
    val checkMinNumOfChar: CheckMinNumOfChar,
    val getWeather: GetWeather,
)
