package com.example.weighttracker.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import com.example.weighttracker.viewmodels.WeightViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_weight.*
import java.lang.Exception
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
            val actuallyWeight: Double = try {
                weightAddEditText.text.toString().toDouble()
            }catch (e: Exception){
                Toast.makeText(context, "Your weight is very strange. I set 0.0", Toast.LENGTH_SHORT)
                    .show()
                0.0
            }

            val weight = Weight(actuallyWeight, Date().time)
            viewModel.insertWeight(weight)

        }



    }

}
