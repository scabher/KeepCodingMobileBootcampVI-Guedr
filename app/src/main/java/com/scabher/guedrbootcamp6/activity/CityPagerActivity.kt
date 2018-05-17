package com.scabher.guedrbootcamp6.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.fragment.CityPagerFragment
import kotlinx.android.synthetic.main.activity_city_pager.*

class CityPagerActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CITY_IDX = "EXTRA_CITY_IDX"

        fun intent(context: Context, cityIndex: Int): Intent {
            val intent = Intent(context, CityPagerActivity::class.java)
            intent.putExtra(EXTRA_CITY_IDX, cityIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        // toolbar.setLogo(R.mipmap.ic_launcher) -> Muestra el logo de la app en la toolbar
        setSupportActionBar(toolbar)    // Fija como ActionBar el componente de la vista 'toolbar'
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el botón de back

        val initialCityIdx = intent.getIntExtra(EXTRA_CITY_IDX, 0)

        // Se crea, si hace falta, el CityPagerFragment pasándole la ciudad inicial
        if (fragmentManager.findFragmentById(R.id.view_pager_fragment) == null) {
            // Hay hueco y habiendo hueco se mete el fragment
            val fragment = CityPagerFragment.newInstance(initialCityIdx)
            supportFragmentManager.beginTransaction()
                    .add(R.id.view_pager_fragment, fragment)
                    .commit()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> { // Flecha de back del toolbar
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
