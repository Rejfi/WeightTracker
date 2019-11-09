package com.example.weighttracker.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel
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
                val stringDate = "$day-$month-$year"
                val sdf = SimpleDateFormat("dd-M-yyyy")
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
        }

    }

}

