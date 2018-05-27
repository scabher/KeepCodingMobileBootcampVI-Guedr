package com.scabher.guedrbootcamp6.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.model.TemperatureUnit
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsDialog: DialogFragment() {

    companion object {
        val ARG_UNITS = "ARG_UNITS"

        fun newInstance(initialUnits: TemperatureUnit): SettingsDialog {
            val arguments = Bundle()
            arguments.putSerializable(ARG_UNITS, initialUnits)

            val dialog = SettingsDialog()
            dialog.arguments = arguments

            return dialog
        }
    }

    val initialUnits by lazy {
        arguments?.getSerializable(ARG_UNITS)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_settings, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // Animación al arrancar
        units_rg.visibility = View.GONE
        units_rg.postDelayed({
            units_rg.visibility = View.VISIBLE
        }, resources.getInteger(R.integer.default_fake_delay).toLong())
    }

    private fun cancelSettings() {
        // Se indica que cancelamos el envío de datos
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, null)
        dismiss()
    }

    private fun acceptSetting() {
        // Creamos los datos de regreso, en este caso las unidades elegidas
        val returnIntent = Intent()
        when (units_rg.checkedRadioButtonId) {
            R.id.celsius_rb -> returnIntent.putExtra(ARG_UNITS, TemperatureUnit.CELSIUS)
            R.id.farenheit_rb -> returnIntent.putExtra(ARG_UNITS, TemperatureUnit.FAHRENHEIT)
        }

        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, returnIntent)
        dismiss()
    }
}