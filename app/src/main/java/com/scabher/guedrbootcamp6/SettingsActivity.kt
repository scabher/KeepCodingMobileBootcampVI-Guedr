package com.scabher.guedrbootcamp6

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import java.time.temporal.TemporalUnit

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
        finish()  // Vuelve a la pantalla anterior
    }

    private fun acceptSetting() {
        finish()
    }
}
