package com.rakangsoftware.weatherapp.screen.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rakangsoftware.weatherapp.domain.weather.Weather

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    val state by weatherViewModel.state.collectAsState()
    val search by weatherViewModel.searchText.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Weather App") },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )

        TextField(
            value = search,
            onValueChange = { weatherViewModel.processEvent(WeatherViewModel.UIEvent.Search(it)) },
            label = { Text(text = "Enter city name") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onBackground,

            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        when (state) {
            WeatherViewModel.UIState.Init -> {}
            is WeatherViewModel.UIState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherViewModel.UIState.Success -> {
                val weather = (state as WeatherViewModel.UIState.Success).weather
                WeatherCard(weather)
            }
            is WeatherViewModel.UIState.Error -> {
                val message = (state as WeatherViewModel.UIState.Error).message
                Text(message)
            }
        }
    }
}

@Composable
fun WeatherCard(weather: Weather) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(weather.location, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${weather.temperature}°C", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(weather.condition)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = weather.iconUrl,
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .size(128.dp)
                    .clip(shape = MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
        }
    }
}
