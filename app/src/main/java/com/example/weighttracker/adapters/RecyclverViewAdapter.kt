package com.example.weighttracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttracker.R

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.weight_row,parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val weightTextView = view.findViewById<TextView>(R.id.weightTextView)
            val dateTextView = view.findViewById<TextView>(R.id.dateTextView)
            val weightCardView = view.findViewById<CardView>(R.id.weightCardView)

    }
}