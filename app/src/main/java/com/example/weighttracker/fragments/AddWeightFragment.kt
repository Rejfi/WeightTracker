package com.example.weighttracker.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider

import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_weight.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddWeightFragment : Fragment() {

    private lateinit var viewModel: WeightViewModel
    private var date: Date = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_weight, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(WeightViewModel::class.java)

        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener{
            @SuppressLint("SimpleDateFormat")
            override fun onSelectedDayChange(cv: CalendarView, year: Int, month: Int, day: Int) {
                val stringDate = "$day-${month+1}-$year"
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                try {
                    date = sdf.parse(stringDate)
                }catch (e: Exception){
                    throw e
                }
            }
        })
        submitWeightButton.setOnClickListener {
            val actuallyWeight: Double = try {
                if(weightAddEditText.text.toString().trim().contains(',', false)){
                    weightAddEditText.text.toString().replace(',','.')
                }
                weightAddEditText.text.toString().toDouble()

            }catch (e: Exception){
                Toast.makeText(context, "Your weight is very strange. Check your data", Toast.LENGTH_SHORT)
                    .show()
                0.0
            }
            val userDate = date.time
            if(actuallyWeight > 0.0){
                val weight = Weight(actuallyWeight, userDate)
                viewModel.insertWeight(weight)
            }
            activity!!.findViewById<CalendarView>(R.id.calendarView).clearFocus()
            activity!!.findViewById<EditText>(R.id.weightAddEditText).isClickable = false
            activity!!.findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawer(GravityCompat.START)
        }
    }

}

