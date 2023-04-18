package com.rakangsoftware.weatherapp.screen.weather

import com.rakangsoftware.weatherapp.core.Async
import com.rakangsoftware.weatherapp.core.BaseViewModel
import com.rakangsoftware.weatherapp.domain.weather.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherViewModel(
    private val useCases: WeatherUseCases,
) : BaseViewModel() {

    sealed class UIEvent {
        data class Search(val location: String) : UIEvent()
    }

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText

    private val _state = MutableStateFlow<Async<Weather>?>(null)
    val state: StateFlow<Async<Weather>?>
        get() = _state

    fun processEvent(event: UIEvent) {
        when (event) {
            is UIEvent.Search -> searchWeather(event.location)
        }
    }

    private fun searchWeather(location: String) {
        _searchText.value = location
        execute({ useCases.getWeather(location) }) {
            _state.value = it
        }
    }
}
