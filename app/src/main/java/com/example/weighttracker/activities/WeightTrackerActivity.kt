package com.example.weighttracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.weighttracker.R
import com.example.weighttracker.fragments.WeightTrackerFragment
import com.google.android.material.tabs.TabLayout

class WeightTrackerActivity : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Odpalenie na probe fragmentu WeightTrackerFragment



    }

}
