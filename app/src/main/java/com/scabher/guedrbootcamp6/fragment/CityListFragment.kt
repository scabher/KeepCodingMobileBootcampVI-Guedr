package com.scabher.guedrbootcamp6.fragment

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

/**
 * A simple [Fragment] subclass.
 * Use the [CityListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CityListFragment : Fragment() {

    companion object {
        @JvmStatic // Indica que el método es estático (opcional)
        fun newInstance() = CityListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cities = Cities()
        val adapter = ArrayAdapter<City>(
                activity,
                android.R.layout.simple_list_item_1,
                cities.toArray())

        city_list.adapter = adapter
    }
}
