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

        //Chart appearance settings
        chart = view!!.findViewById(R.id.weight_chart)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.valueFormatter = MyAxisValueFormatter()
        chart.isAutoScaleMinMaxEnabled = true
        chart.axisRight.isEnabled = false
        chart.axisLeft.textSize = 15f
        chart.description = null

        //Data retrieve and draw
        viewModel.getAllWeights().observe(viewLifecycleOwner, Observer {

            val arrayOfEntry = ChartHelper.listWeightToEntry(it)
                dataSet = LineDataSet(arrayOfEntry, null)
                dataSet.valueTextSize = 0f
                dataSet.lineWidth = 5f
                dataSet.setCircleColor(Color.GREEN)
                dataSet.circleRadius = 5f
                dataSet.disableDashedHighlightLine()
                dataSet.formLineWidth = 0f

                //Set lineData and draw it
                lineData = LineData(dataSet)
                chart.data = lineData
                chart.axisLeft.axisMinimum = chart.yMin-20
                chart.axisLeft.axisMaximum = chart.yMax+20
                chart.xAxis.spaceMin = 8640000f
                chart.xAxis.spaceMax = 2*8640000f
                chart.invalidate()
        })

    }

   private inner class MyAxisValueFormatter: IndexAxisValueFormatter(){
        override fun getFormattedValue(value: Float): String {
            return SimpleDateFormat("dd-MM-YY", Locale.getDefault()).format(Date(value.toLong()))
        }
    }

}

