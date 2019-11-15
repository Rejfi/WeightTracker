package com.example.weighttracker.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weighttracker.fragments.MyGoalsFragment
import com.example.weighttracker.fragments.WeightChart
import com.example.weighttracker.fragments.WeightTrackerFragment

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> WeightTrackerFragment()
            1 -> WeightChart()
            2 -> MyGoalsFragment()
            else -> WeightTrackerFragment()
        }
    }

    override fun getCount(): Int {
            return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Your history"
            1 -> "Chart"
            2 -> "Stats"
            else -> ""
        }
    }

}