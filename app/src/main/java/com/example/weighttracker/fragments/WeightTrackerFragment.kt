package com.example.weighttracker.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttracker.R
import com.example.weighttracker.adapters.OnLongItemClickListener
import com.example.weighttracker.adapters.RecyclerViewAdapter
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel

class WeightTrackerFragment : Fragment(), View.OnCreateContextMenuListener{
    private lateinit var viewModel: WeightViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weight_tracker_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(WeightViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = view!!.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 3)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter


        //Delete notes by swipe
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