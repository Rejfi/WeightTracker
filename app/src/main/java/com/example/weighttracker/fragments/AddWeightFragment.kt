package com.example.weighttracker.fragments


import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel
import kotlinx.android.synthetic.main.fragment_add_weight.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddWeightFragment : Fragment() {

    private lateinit var viewModel: WeightViewModel

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

        submitWeightButton.setOnClickListener {

            val today = SimpleDateFormat.getTimeInstance().calendar.time
            val weight = Weight(weightAddEditText.text.toString().toDouble(), today.time)
            viewModel.insertWeight(weight)

        }

    }

}
