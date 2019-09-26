package com.example.weighttracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttracker.R
import com.example.weighttracker.adapters.RecyclerViewAdapter
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
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

        val adapter = RecyclerViewAdapter()

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(WeightViewModel::class.java)
        recyclerView = view!!.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 3)
        recyclerView.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteWeight(adapter.getWeightAtPosition(viewHolder.adapterPosition))

            }

        }).attachToRecyclerView(recyclerView)


        viewModel.getAllWeights().observe(viewLifecycleOwner, Observer<List<Weight>> {
            //Update recyclerView adapter to show current notes
            adapter.submitList(it)
        })



    }



}