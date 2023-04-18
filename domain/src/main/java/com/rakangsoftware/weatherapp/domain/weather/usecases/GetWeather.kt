package com.rakangsoftware.weatherapp.domain.weather.usecases

import com.rakangsoftware.weatherapp.domain.common.AppException
import com.rakangsoftware.weatherapp.domain.weather.Weather
import com.rakangsoftware.weatherapp.domain.weather.repository.WeatherRepository

class GetWeather(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(location: String): Weather {
        if (location.length < 3) {
            throw AppException.SearchTermTooShortException(3)
        }
        return weatherRepository.getWeather(location)
    }
}
