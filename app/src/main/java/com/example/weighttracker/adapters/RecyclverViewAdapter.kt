package com.example.weighttracker.adapters

import android.annotation.SuppressLint
import android.text.style.TtsSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeParseException
import java.util.*

class RecyclerViewAdapter: ListAdapter<Weight, RecyclerViewAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.weight_row,parent, false))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Weight>() {
            override fun areItemsTheSame(oldItem: Weight, newItem: Weight): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Weight, newItem: Weight): Boolean {
                return oldItem.weight == newItem.weight && oldItem.date == newItem.date

            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val weight = getItem(holder.adapterPosition)
            val weightDate = Date(weight.date)
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(weightDate)

            holder.weightTextView.text = weight.weight.toString()
            holder.dateTextView.text = date.toString()


    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val weightTextView: TextView = view.findViewById(R.id.weightTextView)
            val dateTextView: TextView = view.findViewById(R.id.dateTextView)
            val weightCardView: CardView = view.findViewById(R.id.weightCardView)

    }

    fun getWeightAtPosition(position: Int): Weight {
        return getItem(position)
    }
}