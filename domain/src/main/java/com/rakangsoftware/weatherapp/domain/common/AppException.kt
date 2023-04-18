package com.rakangsoftware.weatherapp.domain.common

sealed class AppException : Exception() {
    class SearchTermTooShortException(val min: Int) : AppException()
}