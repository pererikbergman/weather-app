package com.rakangsoftware.weatherapp.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakangsoftware.weatherapp.domain.common.Result
import com.rakangsoftware.weatherapp.domain.weather.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val useCases: WeatherUseCases,
) : ViewModel() {

    sealed class UIState {
        object Init : UIState()
        object Loading : UIState()
        data class Success(val weather: Weather) : UIState()
        data class Error(val message: String) : UIState()
    }

    sealed class UIEvent {
        data class Search(val location: String) : UIEvent()
    }

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText

    private val _state = MutableStateFlow<UIState>(UIState.Init)
    val state: StateFlow<UIState>
        get() = _state

    fun processEvent(event: UIEvent) {
        when (event) {
            is UIEvent.Search -> searchWeather(event.location)
        }
    }

    private fun searchWeather(location: String) {
        _searchText.value = location
        _state.value = UIState.Loading

        viewModelScope.launch {
            when (val result = useCases.checkMinNumOfChar(location)) {
                is Result.Success ->  doNetworkCall(location)
                is Result.Error   -> _state.value =
                    UIState.Error(result.exception.message ?: "Unknown error occurred")
            }
        }
    }

    private suspend fun doNetworkCall(
        location: String,
    ) {
        when (val result = useCases.getWeather(location)) {
            is Result.Success -> _state.value = UIState.Success(result.data)
            is Result.Error   -> _state.value =
                UIState.Error(result.exception.message ?: "Unknown error occurred")
        }
    }
}
