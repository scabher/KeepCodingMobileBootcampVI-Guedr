package com.scabher.guedrbootcamp6.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.scabher.guedrbootcamp6.*
import com.scabher.guedrbootcamp6.activity.DetailActivity
import com.scabher.guedrbootcamp6.adapter.ForecastRecyclerViewAdapter
import com.scabher.guedrbootcamp6.model.Cities
import com.scabher.guedrbootcamp6.model.City
import com.scabher.guedrbootcamp6.model.Forecast
import com.scabher.guedrbootcamp6.model.TemperatureUnit
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment: Fragment() {

    companion object {
        val ARG_CITY = "ARG_CITY"

        fun newInstance(city: City): Fragment {
            // Se crea el fragment
            val fragment = ForecastFragment()

            // Se crean los argumentos del fragment
            val arguments = Bundle()
            arguments.putSerializable(ARG_CITY, city)

            // Se asignan los argumentos al fragment
            fragment.arguments = arguments

            // Devolvemos el fragment
            return fragment
        }
    }

    private enum class  VIEW_INDEX(val index: Int) {
        LOADING(0), FORECAST(1)
    }

    val REQUEST_SETTINGS = 1

    var forecast: List<Forecast>? = null
        set(value) {
            field = value
            if (value != null) {
                forecast_list?.adapter = ForecastRecyclerViewAdapter(value)
                setRecyclerViewClickListener()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Indica si este fragment publica opciones de menú
        setHasOptionsMenu(true)
    }

    // Ya la vista está creada y accesible
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se configuran las animaciones para el ViewSwitcher (igual para cualquier Switcher)
        view_switcher.setInAnimation(activity, android.R.anim.fade_in)
        view_switcher.setOutAnimation(activity, android.R.anim.fade_out)

        // Se hace que ViewSwitcher muestra la primera vista
        view_switcher.displayedChild = VIEW_INDEX.LOADING.index

        // Para simular el retardo de la carga de datos
        view.postDelayed({
            // Se configura el RecyclerView. Primero se dice cómo se visualizan sus elementos
            // forecast_list.layoutManager = LinearLayoutManager(activity)
            forecast_list?.layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.forecast_columns))

            // Se dice quién es el que anima al RecyclerView
            forecast_list?.itemAnimator = DefaultItemAnimator()

            // Si se pulsa una fila, avisa del evento

            // Datos para rellenar el RecyclerView, tarea del setter de forecast
            val city = arguments?.getSerializable(ARG_CITY) as City
            forecast = city.forecast
            view_switcher?.displayedChild = VIEW_INDEX.FORECAST.index
        }, 3000 /*R.integer.default_fake_delay.toLong()*/)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        // Pilla el xml donde se define el menú y lo transforma dentro del objeto menú
        inflater?.inflate(R.menu.activity_forecast, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_settings -> {
                // Lanzamos la pantalla de ajustes
                // la propiedad activity de los fragments es una referencia a la actividad que lo contiene
                // startActivityForResult(SettingsActivity.intent(activity!!, getTemperatureUnits(activity!!)), REQUEST_SETTINGS)
                val dialog = SettingsDialog.newInstance(getTemperatureUnits(activity!!))
                dialog.setTargetFragment(this, REQUEST_SETTINGS)
                dialog.show(fragmentManager, null)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_SETTINGS -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Volvemos de settings con datos sobre las unidades elegidas por el usuario
                    val newUnits = data.getSerializableExtra(SettingsDialog.ARG_UNITS) as TemperatureUnit
                    val oldUnits = getTemperatureUnits(activity!!)

                    // Guardamos las preferencias del usuario
                    setTemperatureUnits(activity!!, newUnits)

                    // Actualizo la interfaz
                    updateTempreatureView()

                    val newUnitsString = if (newUnits == TemperatureUnit.CELSIUS) getString(R.string.user_selects_celsius)
                    else getString(R.string.user_selects_fahrenheit)

                    // Toast.makeText(this, newUnitsString, Toast.LENGTH_LONG).show()

                    // Al final como es una closure se puede poner una trailing clousure, como Swift
                    Snackbar.make(view!!, newUnitsString, Snackbar.LENGTH_LONG)
                            .setAction("Deshacer") {
                                // Guardo las unidades anteriores
                                setTemperatureUnits(activity!!, oldUnits)

                                // Actualizo para mostrar las unidades anteriores
                                updateTempreatureView()
                            }
                            .show()
                }
            }
        }
    }

    // Indica si este fragment es visible al usuario o no
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && forecast != null) {
            updateTempreatureView()
        }
    }

    fun setRecyclerViewClickListener() {
        val adapter = forecast_list?.adapter as? ForecastRecyclerViewAdapter

        // Si se pulsa una fila, se maneja el evento
        adapter?.onClickListener = View.OnClickListener {
            // Se ha pulsado un elemento del RecyclerView
            // NOTA: Lo ideal sería crear una interfaz que sea implementado en el contenedor del fragment

            // Posición que ocupa dentro del contenedor
            val forecastIndex = forecast_list.getChildAdapterPosition(it)
            val city = arguments?.getSerializable(ARG_CITY) as City
            val cityIndex = Cities.getIndex(city)

            // Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity!!,
                    it,
                    getString(R.string.transition_to_detail)
            )
            startActivity(DetailActivity.intent(activity!!, cityIndex, forecastIndex), animationOptions.toBundle())
        }
    }

    // Se actualiza la interfaz con las temperaturas
    fun updateTempreatureView() {
        forecast_list?.adapter = ForecastRecyclerViewAdapter(forecast!!)
        setRecyclerViewClickListener()
    }
}