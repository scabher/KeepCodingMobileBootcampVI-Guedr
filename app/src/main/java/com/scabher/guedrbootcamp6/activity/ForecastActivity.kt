package com.scabher.guedrbootcamp6.activity

import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.EditText
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.fragment.CityListFragment
import com.scabher.guedrbootcamp6.fragment.CityListFragment.OnCitySelectedListener
import com.scabher.guedrbootcamp6.fragment.CityPagerFragment
import com.scabher.guedrbootcamp6.model.City
import kotlinx.android.synthetic.main.activity_forecast.*

// AppCompatActivity : Compatibilidad con versiones anteriores de Android.
// () : Se indica para especificar el constructor a usar por defecto
class ForecastActivity: AppCompatActivity(), OnCitySelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // Chuleta para saber detalles del dispositivo real (o emulador) que está ejecutando
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val dpWidth = (width / metrics.density).toInt()
        val dpHeight = (height / metrics.density).toInt()
        val model = Build.MODEL
        val androidVersion = Build.VERSION.SDK_INT


        // Se averigua qué interfaz hemos cargado
        // preguntando si en la interfaz tenemos un Framelayout concreto
        if (findViewById<ViewGroup>(R.id.city_list_fragment) != null) {
            // Se ha cargado una interfaz que tienen el hueco para el fragment CitiListFragment

            // Se comprueba que todavía no está el fragment en la jerarquía
            if (supportFragmentManager.findFragmentById(R.id.city_list_fragment) == null) {

                // Se añade el fragment de forma dinámica
                val fragment = CityListFragment.newInstance()

                // Transacción para añadir un fragment en la pantalla indicando en qué hueco y qué fragment
                supportFragmentManager.beginTransaction()
                        .add(R.id.city_list_fragment, fragment)
                        .commit()
            }
        }

        if (findViewById<ViewGroup>(R.id.view_pager_fragment) != null) {
            // Se ha cargado una interfaz que tienen el hueco para el fragment CityListFragment

            // Se comprueba que todavía no está el fragment en la jerarquía
            if (supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null) {
                // Transacción para añadir un fragment en la pantalla indicando en qué hueco y qué fragment
                supportFragmentManager.beginTransaction()
                        .add(R.id.view_pager_fragment, CityPagerFragment.newInstance(0))
                        .commit()
            }
        }

        add_button.setOnClickListener {
            val customView = layoutInflater.inflate(R.layout.view_cityinput, null)
            val cityNameText = customView.findViewById<EditText>(R.id.city_name)
            AlertDialog.Builder(this)
                    .setTitle("Añadir Ciudad")
                    .setMessage("Introduce la ciudad a añadir")
                    .setView(customView)
                    .setPositiveButton(android.R.string.ok, { _, _ ->
                        // Se añade la ciudad
                        Snackbar.make(findViewById(android.R.id.content), cityNameText.text, Snackbar.LENGTH_LONG).show()
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
        }
    }

    override fun onCitySelected(city: City, position: Int) {
        val cityPagerFragment = supportFragmentManager.findFragmentById(R.id.view_pager_fragment) as? CityPagerFragment

        if (cityPagerFragment != null) {
            // Está en una interfaz donde xiste el CityPagerFragment, se mueve a una ciudad
            cityPagerFragment.moveToCity(position)
        }
        else {
            // Está en una interfaz donde sólo hay lista. Landamos la actividad CityPagerActivity
            val intent = CityPagerActivity.intent(this, position)
            startActivity(intent)
        }

    }

}
