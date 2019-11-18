package com.example.weighttracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttracker.R
import com.example.weighttracker.data.Weight
import java.text.SimpleDateFormat
import java.util.*


class RecyclerViewAdapter: ListAdapter<Weight, RecyclerViewAdapter.MyViewHolder>(DIFF_CALLBACK){

    private lateinit var onLongItemClickListener: OnLongItemClickListener

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.weight_row,parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val weight = getItem(holder.adapterPosition)
            val weightDate = Date(weight.date)
            val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(weightDate)

            holder.weightTextView.text = weight.weight.toString().plus(" kg")
            holder.dateTextView.text = date.toString()
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val weightTextView: TextView = view.findViewById(R.id.weightTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        private val weightCardView: CardView = view.findViewById(R.id.weightCardView)
    }

    fun getWeightAtPosition(position: Int): Weight {
        return getItem(position)
    }

    fun setOnLongItemClickListener(listener: OnLongItemClickListener){
        this.onLongItemClickListener = listener
    }
}

interface OnLongItemClickListener{
    fun onLongItemClick(view: View, position: Int)
}