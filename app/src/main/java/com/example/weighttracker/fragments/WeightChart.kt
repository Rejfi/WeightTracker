package com.example.weighttracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weighttracker.R
import com.example.weighttracker.viewmodels.WeightViewModel
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IDataSet

class WeightChart : Fragment(){

    private lateinit var viewModel: WeightViewModel
    private lateinit var chart: LineChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.weight_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(
            WeightViewModel::class.java)

        chart = view!!.findViewById(R.id.weight_chart)


        val allWeights = viewModel.getAllWeights().observe(viewLifecycleOwner, Observer {

            //Stworzenie nowego wejścia dla wykresu
            var noweDane:ArrayList<Entry> = ArrayList()

            var noweEntry = Entry(it[it.lastIndex].weight.toFloat(), 10f)
            noweDane.add(noweEntry)

            //Stworzenie LineDataSet
            var dataSet: LineDataSet = LineDataSet(noweDane,"Test")
            dataSet.valueTextSize = 20f

            //Stworzenie danych do narysowania
            var lineData = LineData(dataSet)

            chart.data = lineData

            chart.invalidate()
        })





    }


}
