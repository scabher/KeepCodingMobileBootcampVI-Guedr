package com.scabher.guedrbootcamp6.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.forecastDay
import com.scabher.guedrbootcamp6.getTemperatureUnits
import com.scabher.guedrbootcamp6.model.Forecast
import com.scabher.guedrbootcamp6.model.TemperatureUnit
import com.scabher.guedrbootcamp6.unitsToString

class ForecastRecyclerViewAdapter(private val forecast: List<Forecast>): RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    // Define cuál es el interfaz o plantilla
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_forecast, parent, false)

        // Una vez creada esta vista se define a quién avisa cuando se pulsa
        view.setOnClickListener(onClickListener)

        return ForecastViewHolder(view)
    }

    override fun getItemCount() = forecast.size // => return forecast.size

    // Se llama cuando ya tenemos los datos de la fila para mostrarlos
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bindForecast(forecast[position], getTemperatureUnits(holder.itemView.context), position)
    }

    // Clase interna
    // Se encarga de rellenar una fila
    inner class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val day = itemView.findViewById<TextView?>(R.id.day)
        val forecastImage = itemView.findViewById<ImageView?>(R.id.forecast_image)
        val maxTemp = itemView.findViewById<TextView?>(R.id.max_temp)
        val minTemp = itemView.findViewById<TextView?>(R.id.min_temp)
        val humidity = itemView.findViewById<TextView?>(R.id.humidity)
        val forecastDescription = itemView.findViewById<TextView?>(R.id.forecast_description)
        val context = itemView.context // Todas las vistas tienen un contexto y dentro de él tiene el método getString


        // Datos necesarios para rellenar una fila
        fun bindForecast(forecast: Forecast, temperatureUnit: TemperatureUnit, dayNumber: Int) {
            // Se actualiza la vista con el modelo
            day?.text = forecastDay(itemView.context, dayNumber)
            forecastImage?.setImageResource(forecast.icon)
            forecastDescription?.text = forecast.description

            updateTempreatureView(forecast, temperatureUnit)
            humidity?.text = context.getString(R.string.humidity_format, forecast.humidity)
        }

        // Se actualiza la interfaz con las temperaturas
        fun updateTempreatureView(forecast: Forecast, temperatureUnit: TemperatureUnit) {
            val unitsString = unitsToString(temperatureUnit)
            maxTemp?.text = context.getString(R.string.max_temp_format, forecast?.getMaxTemp(temperatureUnit), unitsString)
            minTemp?.text = context.getString(R.string.min_temp_format, forecast?.getMinTemp(temperatureUnit), unitsString)
        }
    }
}