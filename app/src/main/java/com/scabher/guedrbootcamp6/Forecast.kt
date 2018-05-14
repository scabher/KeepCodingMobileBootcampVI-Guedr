package com.scabher.guedrbootcamp6

import kotlin.math.max


enum class TemperatureUnit {
    CELSIUS,
    FAHRENHEIT
}

// Con 'var' crea las propiedades y son públicas
// Con 'val' no las crea, con lo que hay que crear los getters y setters.
// data class : Crea los getter y los setters de todos los parámetros que se pasan al constructor
// se hacen privadas las temperaturas para crear los getter y setters para ellas
data class Forecast (private val maxTemp: Float, private val minTemp: Float, val humidity: Float, val description: String, val icon: Int) {

    protected fun toFahrenheit(celsius: Float) = celsius * 1.8f + 32

    init {
        if (humidity !in 0f..100f) {
            throw IllegalArgumentException("Humidity should be between 0f and 100f")
        }
    }

    // Getter para una propiedad de una data class
    fun getMaxTemp(units: TemperatureUnit) =
        when(units) {
            TemperatureUnit.CELSIUS -> maxTemp
            TemperatureUnit.FAHRENHEIT -> toFahrenheit(maxTemp)
        }

    fun getMinTemp(units: TemperatureUnit) =
            when(units) {
                TemperatureUnit.CELSIUS -> minTemp
                TemperatureUnit.FAHRENHEIT -> toFahrenheit(minTemp)
            }

}