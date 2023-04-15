package com.rakangsoftware.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    val location: Location? = null, val current: Current? = null, val error: Error? = null,
) {
    @Serializable
    data class Location(val name: String, val country: String)

    @Serializable
    data class Current(
        val temp_c: Double, val condition: Condition,
    ) {
        @Serializable
        data class Condition(val text: String, val icon: String)
    }

    @Serializable
    data class Error(val code: Int, val message: String)

    fun hasError(): Boolean {
        return error != null
    }
}
