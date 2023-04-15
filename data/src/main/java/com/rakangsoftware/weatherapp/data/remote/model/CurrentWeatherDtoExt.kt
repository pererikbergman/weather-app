package com.rakangsoftware.weatherapp.data.remote.model

import com.rakangsoftware.weatherapp.domain.weather.Weather

fun CurrentWeatherDto.toDomainWeather(): Weather {
    return Weather(
        location = "${location!!.name}, ${location.country}",
        temperature = current!!.temp_c.toInt(),
        condition = current.condition.text,
        iconUrl = "https:${current.condition.icon}"
    )
}
