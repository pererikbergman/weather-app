package com.rakangsoftware.weatherapp.domain.weather.usecases

import com.rakangsoftware.weatherapp.domain.common.Result

class CheckMinNumOfChar() {
    suspend operator fun invoke(location: String, minNumOfChar: Int = 3): Result<Boolean> {
        return if (location.length >= minNumOfChar) {
            Result.Success<Boolean>(true)
        } else {
            Result.Error(Exception("Search term have to be at lease $minNumOfChar characters long."))
        }
    }
}
