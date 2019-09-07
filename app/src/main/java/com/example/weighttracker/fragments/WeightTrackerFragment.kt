package com.example.weighttracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttracker.R
import com.example.weighttracker.adapters.RecyclerViewAdapter
import com.example.weighttracker.viewmodels.WeightViewModel
import kotlinx.android.synthetic.main.weight_tracker_fragment.*

class WeightTrackerFragment : Fragment() {

    private lateinit var viewModel: WeightViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weight_tracker_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(WeightViewModel::class.java)
        recyclerView = view!!.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 3)
        recyclerView.adapter = RecyclerViewAdapter()
    }



}