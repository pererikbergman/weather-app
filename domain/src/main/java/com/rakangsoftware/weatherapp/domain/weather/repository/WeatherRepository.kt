package com.rakangsoftware.weatherapp.domain.weather.repository

import com.rakangsoftware.weatherapp.domain.weather.Weather

interface WeatherRepository {
    suspend fun getWeather(location: String): Weather
}
