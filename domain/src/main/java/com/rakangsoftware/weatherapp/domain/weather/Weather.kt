package com.rakangsoftware.weatherapp.domain.weather

data class Weather(
    val location: String,
    val temperature: Int,
    val condition: String,
    val iconUrl: String
)
