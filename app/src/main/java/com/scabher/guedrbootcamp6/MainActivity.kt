package com.scabher.guedrbootcamp6

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class MainActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = MainActivity::class.java.canonicalName
//    lateinit var forecastImage: ImageView   // lateinit indica que me hago cargo de que no sea null antes de usarlo

    val forecastImage by lazy {
        findViewById<ImageView>(R.id.forecastImage) // Se ejecuta la primera vez que se accede, se inicializa con la última línea de la "closure"
    }

    // ? : Existen tipos opcionales como en Swift, C#, ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Carga el layout de inicio
        setContentView(R.layout.activity_main)

//        val europeanButton = findViewById<Button>(R.id.european_system_button)
//        val europeanButton: Button = findViewById(R.id.european_system_button)
        val europeanButton = findViewById(R.id.european_system_button) as? Button
        val americanButton = findViewById(R.id.american_system_button) as? Button

//        forecastImage = findViewById(R.id.forecastImage)

//        europeanButton?.let {
//            europeanButton?.setOnClickListener(this)
//        }
        // Kotlin sabe que dentro del if europeanButton no es null, como el "if let" de Swift
//        if (europeanButton != null) {
//            europeanButton.setOnClickListener(this)
//        }
//
//        if (americanButton != null) {
//            americanButton.setOnClickListener(this)
//        }

        europeanButton?.setOnClickListener {
            forecastImage.setImageResource(R.drawable.offline_weather)
        }

        americanButton?.setOnClickListener {
            forecastImage.setImageResource(R.drawable.offline_weather2)
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

//    override fun onClick(view: View?) {
//        if (view == null) {
//            return
//        }

        // view.getId() es lo mismo que v.id
//        if (view.id == R.id.european_system_button) {
//            Log.v(TAG, "Han pulsado el botón europeo")
//        }
//        else if (view.getId() == R.id.american_system_button) {
//            Log.v(TAG, "Han pulsado el botón americano")
//        }
//        else {
//            Log.v(TAG, "Han pulsado vista desconocida")
//        }

//        Log.v(TAG,
//                when (view.id) {
//                    R.id.european_system_button -> "Han pulsado el botón europeo"
//                    R.id.american_system_button -> {
//                        val a = 2 + 2
//                        Log.v(TAG, "el valor de a: $a")
//                        "Han pulsado el botón americano"
//                    }
//                    else -> "Han pulsado vista desconocida"
//                }
//        )
//
//        val image2Show = when (view.id) {
//            R.id.european_system_button -> R.drawable.offline_weather
//            else -> R.drawable.offline_weather2
//        }
//
//        forecastImage?.setImageResource(image2Show)
//    }
}
