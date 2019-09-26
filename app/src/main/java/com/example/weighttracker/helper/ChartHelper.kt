package com.example.weighttracker.helper

import com.example.weighttracker.data.Weight
import com.github.mikephil.charting.data.Entry
import java.security.KeyStore

class ChartHelper {
    companion object{

        fun listWeightToEntry(listWeight: List<Weight>): ArrayList<Entry> {

            val arrayEntry = ArrayList<Entry>()
            for (i in listWeight){
                val entry = Entry()
                entry.y = i.weight.toFloat()
                entry.x = i.date.toFloat()
                arrayEntry.add(entry)
            }

            return arrayEntry
        }
    }



}