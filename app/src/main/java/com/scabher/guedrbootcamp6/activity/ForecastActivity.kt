package com.scabher.guedrbootcamp6.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scabher.guedrbootcamp6.R

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }

}
