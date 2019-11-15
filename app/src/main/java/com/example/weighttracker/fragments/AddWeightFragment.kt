package com.example.weighttracker.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel
import kotlinx.android.synthetic.main.fragment_add_weight.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(WeightViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        calendarView.setOnDateChangeListener { cv, year, month, day ->
            val currentWeight: Double = getCurrentWeight()
            val userDate = getCurrentDate(year, month, day).time

            if (currentWeight > 0.0) {
                val weight = Weight(currentWeight, userDate)
                viewModel.insertWeight(weight)
            }
            //Close navigation drawer
            activity!!.findViewById<DrawerLayout>(R.id.drawer_layout)
                .closeDrawer(GravityCompat.START)
        }
    }

    /**
     * Get the current weight from editText. Return weight if can be convert, return 0.0 otherwise
     */
    private fun getCurrentWeight(): Double{
        return try {
            if (weightAddEditText.text.toString().trim().contains(',', false)) {
                weightAddEditText.text.toString().replace(',', '.')
            }
            weightAddEditText.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Your weight is very strange. Check your data",
                Toast.LENGTH_SHORT
            ).show()
            0.0 //return
        }
    }

    /**
     * Get the current date. Return Date()
     */
    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(year: Int, month: Int, day: Int): Date{
        val stringDate = "$day-${month + 1}-$year"
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        try {
            date = sdf.parse(stringDate)
        } catch (e: Exception) {
            throw e
        }
        return Date()
    }
}

