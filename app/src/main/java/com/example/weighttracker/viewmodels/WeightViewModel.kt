package com.example.weighttracker.viewmodels

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weighttracker.data.Weight
import com.example.weighttracker.data.WeightRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class WeightViewModel(application: Application): AndroidViewModel(application) {

    val x = 10

    private val weightRepository = WeightRepository(application)

    private var allWeights: Deferred<LiveData<Map<Long, Double>>> =
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

}