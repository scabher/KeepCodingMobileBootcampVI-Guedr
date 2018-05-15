package com.scabher.guedrbootcamp6.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.scabher.guedrbootcamp6.R
import com.scabher.guedrbootcamp6.fragment.ForecastFragment
import com.scabher.guedrbootcamp6.model.Cities
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


    private val cities = Cities()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        // toolbar.setLogo(R.mipmap.ic_launcher) -> Muestra el logo de la app en la toolbar
        setSupportActionBar(toolbar)    // Fija como ActionBar el componente de la vista 'toolbar'
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el botón de back

        val adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ForecastFragment.newInstance(cities.getCity(position))
            }

            override fun getCount(): Int {
                return cities.count
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return cities.getCity(position).name
            }
        }

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                updateCityInfo(position)
            }

        })
        val initialCityIdx = intent.getIntExtra(EXTRA_CITY_IDX, 0)
        moveToCity(initialCityIdx)
        updateCityInfo(initialCityIdx)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.pager_navigation, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?)= when (item?.itemId) {
        R.id.previous -> {
            view_pager.currentItem -= 1
            true
        }
        R.id.next -> {
            view_pager.currentItem += 1
            true
        }
        android.R.id.home -> { // Flecha de back del toolbar
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        val previousMenu = menu?.findItem(R.id.previous)
        val nextMenu = menu?.findItem(R.id.next)
        val adapter = view_pager.adapter!!

        previousMenu?.isEnabled = view_pager.currentItem > 0
        nextMenu?.isEnabled = view_pager.currentItem < adapter.count - 1

        return true
    }

    private fun updateCityInfo(position: Int) {
        supportActionBar?.title = cities.getCity(position).name
    }

    private fun moveToCity(index: Int) {
        view_pager.currentItem = index
    }
}
