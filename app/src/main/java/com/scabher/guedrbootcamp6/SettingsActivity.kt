package com.scabher.guedrbootcamp6

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        ok_btn.setOnClickListener {
            acceptSetting()
        }

        cancel_btn.setOnClickListener {
            cancelSettings()
        }



    }

    private fun cancelSettings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun acceptSetting() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
