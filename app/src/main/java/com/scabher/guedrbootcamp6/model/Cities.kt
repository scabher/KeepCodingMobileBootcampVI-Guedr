package com.scabher.guedrbootcamp6.model

import com.scabher.guedrbootcamp6.R


// Si se define con 'object' se convierte en un Singleton
object Cities {

    private val cities: List<City> = listOf (
        City("S/C Tenerife", listOf (
            Forecast(28f, 22f, 35f, "Soleado", R.drawable.ico_02),
            Forecast(29f, 23f, 36f, "Soleado 1", R.drawable.ico_03),
            Forecast(30f, 24f, 37f, "Soleado 2", R.drawable.ico_04),
            Forecast(31f, 25f, 38f, "Soleado 3", R.drawable.ico_09),
            Forecast(32f, 26f, 39f, "Soleado 4", R.drawable.ico_10)
        )),
        City("Barcelona", listOf (
            Forecast(26f, 20f, 30f, "Soleado con alguna nube", R.drawable.ico_01),
            Forecast(27f, 21f, 31f, "Soleado con alguna nube 1", R.drawable.ico_02),
            Forecast(28f, 22f, 32f, "Soleado con alguna nube 2", R.drawable.ico_03),
            Forecast(29f, 23f, 33f, "Soleado con alguna nube 3", R.drawable.ico_04),
            Forecast(30f, 24f, 34f, "Soleado con alguna nube 4", R.drawable.ico_09)
        )),
        City("Madrid", listOf (
            Forecast(25f, 11f, 40f, "Parcialmente nublado", R.drawable.ico_03),
            Forecast(26f, 12f, 41f, "Parcialmente nublado 1", R.drawable.ico_04),
            Forecast(27f, 13f, 42f, "Parcialmente nublado 2", R.drawable.ico_09),
            Forecast(28f, 14f, 43f, "Parcialmente nublado 3", R.drawable.ico_10),
            Forecast(29f, 15f, 44f, "Parcialmente nublado 4", R.drawable.ico_11)
        ))
    )

    val count
        get() = cities.size

    fun getCity(index: Int) = cities[index]

    fun getIndex(city: City) = cities.indexOf(city)

    // Sobreescritura de []
    operator fun get(index: Int) = cities[index]

    fun toArray() = cities.toTypedArray()
}