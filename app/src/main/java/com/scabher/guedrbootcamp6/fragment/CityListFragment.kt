package com.scabher.guedrbootcamp6.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.model.Cities
import com.scabher.guedrbootcamp6.model.City
import kotlinx.android.synthetic.main.fragment_city_list.*

class CityListFragment : Fragment() {

    companion object {
        @JvmStatic // Indica que el método es estático (opcional)
        fun newInstance() = CityListFragment()
    }

    private var onCitySelectedListener: OnCitySelectedListener? = null

    interface OnCitySelectedListener {
        fun onCitySelected(city: City, position: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cities = Cities()
        val adapter = ArrayAdapter<City>(
                activity,
                android.R.layout.simple_list_item_1,
                cities.toArray())

        city_list.adapter = adapter

        // Cuando no se usan los parámetro se sustituyen sus nombres por _
        city_list.setOnItemClickListener { _, _, index, _ ->
            // Se avisa al listener que una ciudad ha sido seleccionada
            onCitySelectedListener?.onCitySelected(cities[index], index)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onCitySelectedListener = null
    }

    private fun commonAttach(activity: Activity?) {
        if (activity is OnCitySelectedListener) {
            onCitySelectedListener = activity
        }
        else {
            onCitySelectedListener = null
        }
    }
}
