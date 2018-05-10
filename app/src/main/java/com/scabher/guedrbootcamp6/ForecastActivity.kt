package com.scabher.guedrbootcamp6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_forecast.*

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class ForecastActivity : AppCompatActivity() {

    // Atributos estáticos
    companion object {
        val TAG = ForecastActivity::class.java.canonicalName
        val REQUEST_SETTINGS = 1
        val PREFERENCE_UNITS = "PREFERENCE"

        fun metodoEstatico() {

        }
    }

    var forecast: Forecast? = null
        set(value) {
            if (value == null) {
                return
            }

            field = value
            forecast_image.setImageResource(value.icon)
            forecast_description.text = value.description

            updateTempreatureView()
            humidity.text = getString(R.string.humidity_format, value.humidity)
        }

    val units: TemperatureUnit
        get() = when(PreferenceManager.getDefaultSharedPreferences(this)
                    .getInt(PREFERENCE_UNITS, TemperatureUnit.CELSIUS.ordinal)) {
                TemperatureUnit.CELSIUS.ordinal -> TemperatureUnit.CELSIUS
                else -> TemperatureUnit.FAHRENHEIT
        }

    // ? : Existen tipos opcionales como en Swift, C#, ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Carga el layout de inicio
        setContentView(R.layout.activity_forecast)



        forecast = Forecast(25f,
                10f,
                35f,
                "Soleado con alguna nube",
                R.drawable.ico_01)

        // Ejecutar método estático ForecastActivity.metodoEstatico()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // super.onCreateOptionsMenu(menu) --> no hace falta ponerlo porque no hace nada

        // Pilla el xml donde se define el menú y lo transforma dentro del objeto menú
        menuInflater.inflate(R.menu.activity_forecast, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_settings -> {
                // Lanzamos la pantalla de ajustes
                startActivityForResult(SettingsActivity.intent(this, units), REQUEST_SETTINGS)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_SETTINGS -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Volvemos de settings con datos sobre las unidades elegidas por el usuario
                    val newUnits = data.getSerializableExtra(SettingsActivity.EXTRA_UNITS) as TemperatureUnit

                    // Guardamos las preferencias del usuario
                    PreferenceManager.getDefaultSharedPreferences(this)
                            .edit()
                            .putInt(PREFERENCE_UNITS, newUnits.ordinal)
                            .apply()

                    // Actualizo la interfaz
                    updateTempreatureView()
                }
            }
        }
    }

    // Se actualiza la interfaz con las temperaturas
    fun updateTempreatureView() {
        val unitsString = unitsToString()
        max_temp.text = getString(R.string.max_temp_format, forecast?.getMaxTemp(units), unitsString)
        min_temp.text = getString(R.string.min_temp_format, forecast?.getMinTemp(units), unitsString)
    }

    fun unitsToString() = if (units== TemperatureUnit.CELSIUS) "ºC"
        else "F"
}
