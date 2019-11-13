package com.example.weighttracker.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.weighttracker.R
import com.example.weighttracker.viewmodels.WeightViewModel
import kotlinx.android.synthetic.main.fragment_my_goals.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MyGoalsFragment : Fragment() {

    private lateinit var viewModel: WeightViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_goals, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(
            WeightViewModel::class.java)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getAllWeights().observe(viewLifecycleOwner, Observer {

            val minDate = it.minBy { it.date }
            var sinceDate: String
            try {
                sinceDate = SimpleDateFormat("dd-MM-YY", Locale.getDefault()).format(Date(minDate!!.date))
                statsTrackDate.text = sinceDate
            }catch (e: Exception){
                throw e
            }
        })

    }

}
