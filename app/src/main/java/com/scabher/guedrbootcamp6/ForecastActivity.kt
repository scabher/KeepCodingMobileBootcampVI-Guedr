package com.scabher.guedrbootcamp6

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }

}
