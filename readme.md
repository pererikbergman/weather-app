# Weather App with Clean Architecture

This project demonstrates the implementation of Clean Architecture in a simple weather app. It uses Kotlin, Jetpack Compose, and Koin to showcase best practices for building maintainable, testable, and scalable mobile applications. The app encompasses a domain layer with its business logic, a data layer responsible for fetching data from external sources, and a presentation layer tasked with displaying data to users and managing interactions.

## Overview

The weather app is built using the following components:

- Domain Layer: Contains the core business logic and rules of the application.
- Data Layer: Responsible for fetching data from external sources and handling the conversion between data models and domain entities.
- Presentation Layer: Displays data to users and manages user interactions with the app.

The app also integrates Koin, a lightweight dependency injection framework for Kotlin developers, to manage dependencies across different layers.

## Blog Post Reference

For a detailed explanation of the implementation and concepts used in this project, please refer to the accompanying blog post: [Clean Architecture for Mobile App Development: Building a Weather App](Blog URL)

## Getting Started

1. Clone this repository.
2. Open the project in Android Studio.
3. Obtain an API key from [OpenWeatherMap](https://openweathermap.org/api) and add it to `local.properties` file as `weather_api_key=YOUR_API_KEY`.
4. Build and run the project on an Android device or emulator.

We hope this project serves as a helpful starting point for implementing Clean Architecture in your own mobile applications. Happy coding!
