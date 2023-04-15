package com.rakangsoftware.weatherapp.data.remote.repository

import com.rakangsoftware.weatherapp.data.remote.model.CurrentWeatherDto
import com.rakangsoftware.weatherapp.data.remote.model.toDomainWeather
import com.rakangsoftware.weatherapp.domain.common.Result
import com.rakangsoftware.weatherapp.domain.weather.Weather
import com.rakangsoftware.weatherapp.domain.weather.repository.WeatherRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
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

    override suspend fun getWeather(location: String): Result<Weather> {
        return try {
            val weatherData = fetchWeatherData(location)
            val weather = weatherData.toDomainWeather()
            Result.Success(weather)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    private suspend fun fetchWeatherData(location: String): CurrentWeatherDto {
        val url = "https://api.weatherapi.com/v1/current.json"
        return httpClient.get(url) {
            url {
                parameter("key", apiKey)
                parameter("q", location)
            }
        }.body()
    }
}
