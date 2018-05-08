package com.scabher.guedrbootcamp6

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.canonicalName

    // ? : Existen tipos opcionales como en Swift, C#, ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Carga el layout de inicio
        setContentView(R.layout.activity_main)

        // Synthetic view binding
        european_system_button.setOnClickListener {
            forecast_image.setImageResource(R.drawable.offline_weather)
        }

        american_system_button.setOnClickListener {
            forecast_image.setImageResource(R.drawable.offline_weather2)
        }

        Log.v(TAG, "han llamado a onCreate: Numero = " + savedInstanceState?.get("Numero"))
    }

    // Se usa para guardar datos antes de que se destruya la Activity
    // Esto pasa cuando se guira el dispositivo, cuando sale el teclado, ...
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Diccionario donde se guardan los datos
        outState?.putInt("Numero", 3)
    }
}
