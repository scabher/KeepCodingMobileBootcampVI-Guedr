package com.scabher.guedrbootcamp6

import kotlin.math.max

// Con 'var' crea las propiedades y son públicas
// Con 'val' no las crea, con lo que hay que crear los getters y setters.
// data class : Crea los getter y los setters de todos los parámetros que se pasan al constructor
data class Forecast (val maxTemp: Float, val minTemp: Float, val humidity: Float, val description: String, val icon: Int) {

}