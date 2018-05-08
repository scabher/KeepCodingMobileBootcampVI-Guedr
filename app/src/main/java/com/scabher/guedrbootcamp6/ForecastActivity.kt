package com.scabher.guedrbootcamp6

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forecast.*

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class ForecastActivity : AppCompatActivity() {

    val TAG = ForecastActivity::class.java.canonicalName

    // ? : Existen tipos opcionales como en Swift, C#, ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Carga el layout de inicio
        setContentView(R.layout.activity_forecast)

        val forecast = Forecast(25f,
                10f,
                35f,
                "Soleado con alguna nube",
                R.drawable.ico_01)

        setForecast(forecast)
    }

    fun setForecast(forecast: Forecast) {
        forecast_image.setImageResource(forecast.icon)
        forecast_description.text = forecast.description
        max_temp.text = getString(R.string.max_temp_format, forecast.maxTemp)
        min_temp.text = getString(R.string.min_temp_format, forecast.minTemp)
        humidity.text = getString(R.string.humidity_format, forecast.humidity)
    }
}
