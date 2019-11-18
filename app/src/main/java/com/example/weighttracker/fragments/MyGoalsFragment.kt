package com.example.weighttracker.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.weighttracker.viewmodels.WeightViewModel
import kotlinx.android.synthetic.main.fragment_my_goals.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import com.example.weighttracker.data.Weight


class MyGoalsFragment : Fragment() {

    private val myGoalFragmentTAG = "MY_GOALS_FRAGMENT"
    private lateinit var viewModel: WeightViewModel
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPref: SharedPreferences
    private lateinit var listWeight: List<Weight>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.weighttracker.R.layout.fragment_my_goals, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(
            WeightViewModel::class.java)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPref = activity!!.getPreferences(Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        goalWeight.setText(sharedPref.getFloat("goal_weight", 0f).toString())

        goalWeight.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(inputText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!inputText.isNullOrEmpty()) {
                    editor.putFloat("goal_weight", inputText.toString().toFloat())
                    editor.apply()

                    if(!goalWeight.text.isNullOrEmpty()){
                        try {
                            val goalWeight = (sharedPref.getFloat("goal_weight", 0.0f).toDouble() - listWeight[listWeight.lastIndex].weight)
                            achieveGoalWeight.text = goalWeight.toString()

                        }catch (e: Exception){
                            Log.e(myGoalFragmentTAG, e.message!!)
                        }
                    }
                }
            }
        })


        viewModel.getAllWeights().observe(viewLifecycleOwner, Observer {
            listWeight = it

            if(!it.isNullOrEmpty()){
                //var sinceDate: String
                try {
                   val sinceDate = SimpleDateFormat("dd-MM-YY", Locale.getDefault()).format(Date(it[0].date))
                    statsTrackDate.text = sinceDate
                }catch (e: Exception){
                    Log.e(myGoalFragmentTAG, e.message!!)
                }


            }
        })

    }
}
