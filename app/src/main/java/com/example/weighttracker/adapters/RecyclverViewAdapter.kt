package com.example.weighttracker.adapters

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val weight = getItem(holder.adapterPosition)

            holder.weightTextView.text = weight.weight.toString()
            holder.dateTextView.text = weight.date.toString()
    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val weightTextView = view.findViewById<TextView>(R.id.weightTextView)
            val dateTextView = view.findViewById<TextView>(R.id.dateTextView)
            val weightCardView = view.findViewById<CardView>(R.id.weightCardView)

    }
}