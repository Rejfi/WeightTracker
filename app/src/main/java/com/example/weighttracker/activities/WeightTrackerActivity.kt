package com.example.weighttracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.weighttracker.R
import com.example.weighttracker.adapters.ViewPagerAdapter
import com.example.weighttracker.fragments.AddWeightFragment
import com.example.weighttracker.fragments.WeightTrackerFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_weight.*

class WeightTrackerActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        //Set fragment in Navigation Drawer
        fm.beginTransaction().add(R.id.addWeightDrawer, AddWeightFragment()).commit()

        //Find viewPager and tabLayout
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        //-------------

        //Setup viewpager and tabLayout together
        viewPager.adapter = ViewPagerAdapter(fm)
        tabLayout.setupWithViewPager(viewPager)

        //Floating button opens drawer
        addWeightFB.setOnClickListener {
            drawer_layout.openDrawer(addWeightDrawer)
        }

    }

}
