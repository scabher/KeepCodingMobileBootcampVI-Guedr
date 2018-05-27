package com.scabher.guedrbootcamp6

import android.content.Context
import android.preference.PreferenceManager
import com.scabher.guedrbootcamp6.model.TemperatureUnit

val PREFERENCE_UNITS = "UNITS"

fun getTemperatureUnits(context: Context) = when(PreferenceManager.getDefaultSharedPreferences(context)
        .getInt(PREFERENCE_UNITS, TemperatureUnit.CELSIUS.ordinal)) {
    TemperatureUnit.CELSIUS.ordinal -> TemperatureUnit.CELSIUS
    else -> TemperatureUnit.FAHRENHEIT
}

fun setTemperatureUnits(context: Context, unit: TemperatureUnit) {
    PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putInt(PREFERENCE_UNITS, unit.ordinal)
            .apply()
}