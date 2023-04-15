package com.rakangsoftware.weatherapp.domain.weather.usecases

import com.rakangsoftware.weatherapp.domain.common.Result
import com.rakangsoftware.weatherapp.domain.weather.Weather
import com.rakangsoftware.weatherapp.domain.weather.repository.WeatherRepository

class GetWeather(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(location: String): Result<Weather> {
        return weatherRepository.getWeather(location)
    }
}
