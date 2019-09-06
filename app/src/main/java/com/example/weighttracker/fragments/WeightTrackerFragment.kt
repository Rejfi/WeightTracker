package com.example.weighttracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.weighttracker.R
import com.example.weighttracker.viewmodels.WeightViewModel

class WeightTrackerFragment : Fragment() {

    private lateinit var viewModel: WeightViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weight_tracker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        TODO("Problem with create WeightViewModel class, something with ViewModel and MVVM architecture")
        viewModel = ViewModelProvider(requireActivity()).get(WeightViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(activity!!.applicationContext, viewModel.x, Toast.LENGTH_SHORT)
            .show()

    }


}