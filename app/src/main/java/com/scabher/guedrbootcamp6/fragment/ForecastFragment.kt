package com.scabher.guedrbootcamp6.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.activity.SettingsActivity
import com.scabher.guedrbootcamp6.model.City
import com.scabher.guedrbootcamp6.model.Forecast
import com.scabher.guedrbootcamp6.model.TemperatureUnit
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment: Fragment() {

    companion object {
        val ARG_CITY = "ARG_CITY"

        fun newInstance(city: City): Fragment {
            // Se crea el fragment
            val fragment = ForecastFragment()

            // Se crean los argumentos del fragment
            val arguments = Bundle()
            arguments.putSerializable(ARG_CITY, city)

            // Se asignan los argumentos al fragment
            fragment.arguments = arguments

            // Devolvemos el fragment
            return fragment
        }
    }

    val REQUEST_SETTINGS = 1
    val PREFERENCE_UNITS = "PREFERENCE"

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

    // Getter
    val units: TemperatureUnit
        get() = when(PreferenceManager.getDefaultSharedPreferences(activity)
                .getInt(PREFERENCE_UNITS, TemperatureUnit.CELSIUS.ordinal)) {
            TemperatureUnit.CELSIUS.ordinal -> TemperatureUnit.CELSIUS
            else -> TemperatureUnit.FAHRENHEIT
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Indica si este fragment publica opciones de menú
        setHasOptionsMenu(true)
    }

    // Ya la vista está creada y accesible
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = arguments?.getSerializable(ARG_CITY) as City
        forecast = city.forecast

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        // Pilla el xml donde se define el menú y lo transforma dentro del objeto menú
        inflater?.inflate(R.menu.activity_forecast, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_settings -> {
                // Lanzamos la pantalla de ajustes
                // la propiedad activity de los fragments es una referencia a la actividad que lo contiene
                startActivityForResult(SettingsActivity.intent(activity!!, units), REQUEST_SETTINGS)

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
                    val oldUnits = units

                    // Guardamos las preferencias del usuario
                    PreferenceManager.getDefaultSharedPreferences(activity)
                            .edit()
                            .putInt(PREFERENCE_UNITS, newUnits.ordinal)
                            .apply()

                    // Actualizo la interfaz
                    updateTempreatureView()

                    val newUnitsString = if (newUnits == TemperatureUnit.CELSIUS) getString(R.string.user_selects_celsius)
                    else getString(R.string.user_selects_fahrenheit)

                    // Toast.makeText(this, newUnitsString, Toast.LENGTH_LONG).show()

                    // Al final como es una closure se puede poner una trailing clousure, como Swift
                    Snackbar.make(view!!, newUnitsString, Snackbar.LENGTH_LONG)
                            .setAction("Deshacer") {
                                // Guardo las unidades anteriores
                                PreferenceManager.getDefaultSharedPreferences(activity)
                                        .edit()
                                        .putInt(PREFERENCE_UNITS, oldUnits.ordinal)
                                        .apply()

                                // Actualizo para mostrar las unidades anteriores
                                updateTempreatureView()
                            }
                            .show()
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