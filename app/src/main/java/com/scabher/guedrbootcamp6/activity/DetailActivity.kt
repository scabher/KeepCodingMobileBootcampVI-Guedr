package com.scabher.guedrbootcamp6.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.forecastDay
import com.scabher.guedrbootcamp6.getTemperatureUnits
import com.scabher.guedrbootcamp6.model.Cities
import com.scabher.guedrbootcamp6.unitsToString
import kotlinx.android.synthetic.main.content_forecast.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CITY_INDEX = "EXTRA_CITY_INDEX"
        val EXTRA_FORECAST_INDEX = "EXTRA_FORECAST_INDEX"

        fun intent(context: Context, cityIndex: Int, forecastIndex: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_CITY_INDEX, cityIndex)
            intent.putExtra(EXTRA_FORECAST_INDEX, forecastIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Se sacan los datos con los que se configura la interfaz
        val city = Cities[intent.getIntExtra(EXTRA_CITY_INDEX, 0)]
        val dayIndex = intent.getIntExtra(EXTRA_FORECAST_INDEX, 0)
        val forecast = city.forecast[dayIndex]

        // Se actualiza la interfaz
        day.text = forecastDay(this, dayIndex)
        forecast_image.setImageResource(forecast.icon)
        humidity.text = getString(R.string.humidity_format, forecast.humidity)
        forecast_description.text = forecast.description

        // Dentro de una actividad el contexto es this
        val temperatureUnit = getTemperatureUnits(this)
        val unitsString = unitsToString(temperatureUnit)
        max_temp?.text = getString(R.string.max_temp_format, forecast?.getMaxTemp(temperatureUnit), unitsString)
        min_temp?.text = getString(R.string.min_temp_format, forecast?.getMinTemp(temperatureUnit), unitsString)
    }
}
