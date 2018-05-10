package com.scabher.guedrbootcamp6

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
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

        units_rg.check(R.id.celsius_rb)

    }

    private fun cancelSettings() {
        finish()  // Vuelve a la pantalla anterior
    }

    private fun acceptSetting() {
        finish()
    }
}
