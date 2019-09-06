package com.example.weighttracker.data

import android.app.Application
import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class WeightRepository (app: Application){

    private var weightDao: WeightDao

    private var allWeights: LiveData<Map<Long, Double>>

    //Get database instance
    init {
        val database: WeightDatabase = WeightDatabase.getInstance(
            app.applicationContext
        )!!
        weightDao = database.weightDao()
        allWeights = weightDao.getAllWeights()
    }

    fun insertWeight(weight: Weight){
        CoroutineScope(Dispatchers.IO).launch {
            weightDao.putNewWeight(weight)
        }.start()
    }

    fun updateWeight(weight: Weight){
        CoroutineScope(Dispatchers.IO).launch {
            weightDao.updateWeight(weight)
        }.start()
    }

    fun deleteWeight(weight: Weight){
        CoroutineScope(Dispatchers.IO).launch {
            weightDao.deleteWeight(weight)
        }.start()
    }

    fun getAllWeightsAsync(): Deferred<LiveData<Map<Long, Double>>> =
        CoroutineScope(Dispatchers.IO).async {
           weightDao.getAllWeights()
    }



}