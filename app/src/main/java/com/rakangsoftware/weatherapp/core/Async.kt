package com.rakangsoftware.weatherapp.core

sealed class Async<T> {
    class Success<T>(val data: T) : Async<T>()
    class Loading<T>(val data: T? = null) : Async<T>()
    class Fail<T>(val error: Throwable) : Async<T>()


    operator fun invoke(): T? = when (this) {
        is Fail -> null
        is Loading -> data
        is Success -> data
    }
}