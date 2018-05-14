package com.scabher.guedrbootcamp6.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.model.TemperatureUnit
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_UNITS = "EXTTRA_UNITS"

        fun intent(context: Context, initialUnits: TemperatureUnit): Intent {
            val intent = Intent(context, SettingsActivity::class.java)
            intent.putExtra(EXTRA_UNITS, initialUnits)

            return intent
        }
    }

    val initialUnits by lazy {
        intent.getSerializableExtra(EXTRA_UNITS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        ok_btn.setOnClickListener {
            acceptSetting()
        }

        cancel_btn.setOnClickListener {
            cancelSettings()
        }

        // Decimos qué radiobutton debe estar marcado en función de initialUnits
        // Como en el when el if devuelve la última línea de la sentencia if/else
        units_rg.check(
            if (initialUnits == TemperatureUnit.CELSIUS)
                R.id.celsius_rb
            else
                R.id.farenheit_rb
        )
    }

    private fun cancelSettings() {
        setResult(Activity.RESULT_CANCELED)
        finish()  // Vuelve a la pantalla anterior
    }

    private fun acceptSetting() {
        // Creamos los datos de regreso, en este caso las unidades elegidas
        val returnIntent = Intent()
        when (units_rg.checkedRadioButtonId) {
            R.id.celsius_rb -> returnIntent.putExtra(EXTRA_UNITS, TemperatureUnit.CELSIUS)
            R.id.farenheit_rb -> returnIntent.putExtra(EXTRA_UNITS, TemperatureUnit.FAHRENHEIT)
        }

        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}
