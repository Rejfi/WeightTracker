package com.example.weighttracker.viewmodels

import android.app.Application
import android.provider.ContactsContract
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weighttracker.data.Weight
import com.example.weighttracker.data.WeightRepository
import kotlinx.coroutines.*

class WeightViewModel(application: Application): AndroidViewModel(application) {

    private val weightRepository = WeightRepository(application)
    private var goalWeight: Int = 0
    private var allWeights: Deferred<LiveData<List<Weight>>> =
        weightRepository.getAllWeightsAsync()

    fun insertWeight(weight: Weight){
        weightRepository.insertWeight(weight)
    }

    fun updateWeight(weight: Weight){
        weightRepository.updateWeight(weight)
    }

    fun deleteWeight(weight: Weight) {
        weightRepository.deleteWeight(weight)
    }

    fun getAllWeights() = runBlocking {
           allWeights.await()
    }

    fun getWeightGoal() = goalWeight

}