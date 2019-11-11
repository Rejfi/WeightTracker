package com.example.weighttracker.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weighttracker.R
import com.example.weighttracker.helper.ChartHelper
import com.example.weighttracker.viewmodels.WeightViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.renderer.YAxisRenderer
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeightChart : Fragment(){

    private lateinit var viewModel: WeightViewModel
    private lateinit var chart: LineChart
    private lateinit var dataSet: LineDataSet
    private lateinit var lineData: LineData

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
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        chart.xAxis.valueFormatter = MyAxisValueFormatter()
        chart.isAutoScaleMinMaxEnabled = true
        chart.axisRight.isEnabled = false
        chart.axisRight.axisMinimum = 0f
        chart.axisLeft.axisMinimum = 0f
        chart.description = null

        viewModel.getAllWeights().observe(viewLifecycleOwner, Observer {

                val arrayOfEntry = ChartHelper.listWeightToEntry(it)
                dataSet = LineDataSet(arrayOfEntry, null)
                dataSet.valueTextSize = 10f
                dataSet.lineWidth = 3f

                //Set lineData and draw it
                lineData = LineData(dataSet)
                chart.data = lineData
                chart.invalidate()

        })

    }

   private inner class MyAxisValueFormatter: IndexAxisValueFormatter(){
        override fun getFormattedValue(value: Float): String {
            return SimpleDateFormat("dd-MM", Locale.getDefault()).format(Date(value.toLong()))
        }
    }

}

