package com.scabher.guedrbootcamp6.model

import com.scabher.guedrbootcamp6.R

class Cities {

    private val cities: List<City> = listOf (
        City("S/C Tenerife", Forecast(28f, 22f, 35f, "Soleado", R.drawable.ico_02)),
        City("Barcelona", Forecast(26f, 20f, 30f, "Soleado con alguna nube", R.drawable.ico_01)),
        City("Madrid", Forecast(25f, 10f, 40f, "Parcialmente nublado", R.drawable.ico_03))
    )

    val count
        get() = cities.size

    fun getCity(index: Int) = cities[index]

    // Sobreescritura de []
    operator fun get(index: Int) = cities[index]

    fun toArray() = cities.toTypedArray()
}