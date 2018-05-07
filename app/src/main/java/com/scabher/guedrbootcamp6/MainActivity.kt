package com.scabher.guedrbootcamp6

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class MainActivity : AppCompatActivity() {

    // ? : Existen tipos opcionales como en Swift, C#, ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("UnTag", "han llamado a onCreate: Numero = " + savedInstanceState?.get("Numero"))
    }

    // Se usa para guardar datos antes de que se destruya la Activity
    // Esto pasa cuando se guira el dispositivo, cuando sale el teclado, ...
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Diccionario donde se guardan los datos
        outState?.putInt("Numero", 3)
    }
}
