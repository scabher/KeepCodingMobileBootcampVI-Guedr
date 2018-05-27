package com.scabher.guedrbootcamp6

import android.content.Context
import com.scabher.guedrbootcamp6.model.TemperatureUnit

fun unitsToString(units: TemperatureUnit) = if (units== TemperatureUnit.CELSIUS) "ºC"
else "F"

fun forecastDay(context: Context, index: Int) = when(index) {
    0 -> context.getString(R.string.today)
    1 -> context.getString(R.string.tomorrow)
    2 -> context.getString(R.string.day_after_tomorrow)
    3 -> context.getString(R.string.after_3_days)
    4 -> context.getString(R.string.after_4_days)
    else -> context.getString(R.string.unknown_day)
}