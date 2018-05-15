package com.scabher.guedrbootcamp6.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.fragment.CityListFragment

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // Se comprueba que todavía no está el fragment en la jerarquía
        if (supportFragmentManager.findFragmentById(R.id.city_list_fragment) != null) {
            return
        }

        // Se añade el fragment de forma dinámica
        val fragment = CityListFragment.newInstance()

        // Transacción para añadir un fragment en la pantalla indicando en qué hueco y qué fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.city_list_fragment, fragment)
                .commit()

    }

}
