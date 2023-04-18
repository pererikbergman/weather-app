package com.rakangsoftware.weatherapp.data.remote.repository

import com.rakangsoftware.weatherapp.data.remote.model.CurrentWeatherDto
import com.rakangsoftware.weatherapp.data.remote.model.toDomainWeather
import com.rakangsoftware.weatherapp.domain.weather.Weather
import com.rakangsoftware.weatherapp.domain.weather.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class WeatherRepositoryApi(
    private val apiKey: String,
) : WeatherRepository {

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = true
            })
        }
    }

    override suspend fun getWeather(location: String): Weather {
        return fetchWeatherData(location).toDomainWeather()
    }

    private suspend fun fetchWeatherData(location: String): CurrentWeatherDto {
        val url = "https://api.weatherapi.com/v1/current.json"
        return httpClient.get(url) {
            url {
                parameter("key", apiKey)
                parameter("q", location)
            }
        }.body<CurrentWeatherDto>().also {
            val a = 1
        }
    }
}
