package com.rakangsoftware.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rakangsoftware.weatherapp.screen.weather.WeatherScreen
import com.rakangsoftware.weatherapp.screen.weather.WeatherViewModel
import com.rakangsoftware.weatherapp.ui.theme.WeatherAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                WeatherScreen(viewModel)
            }
        }
    }
}
