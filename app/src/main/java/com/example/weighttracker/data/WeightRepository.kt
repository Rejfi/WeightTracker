package com.example.weighttracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class WeightRepository (application: Application){

    private var weightDao: WeightDao

    private var allWeights: LiveData<List<Weight>>

    //Get database instance
    init {
        val database: WeightDatabase = WeightDatabase.getInstance(
            application.applicationContext
        )!!
        weightDao = database.weightDao()
        allWeights = weightDao.getAllWeights()
    }

    fun insertWeight(weight: Weight){
        CoroutineScope(Dispatchers.IO).launch {
            weightDao.insert(weight)
        }.start()
    }

    fun updateWeight(weight: Weight){
        CoroutineScope(Dispatchers.IO).launch {
            weightDao.update(weight)
        }.start()
    }

    fun deleteWeight(weight: Weight){
        CoroutineScope(Dispatchers.IO).launch {
            weightDao.delete(weight)
        }.start()
    }

    fun getAllWeightsAsync(): Deferred<LiveData<List<Weight>>> =
        CoroutineScope(Dispatchers.IO).async {
           weightDao.getAllWeights()
    }



}