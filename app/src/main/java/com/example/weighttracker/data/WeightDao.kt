package com.example.weighttracker.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeightDao{

    @Insert
    fun putNewWeight(weight: Weight)

    @Update
    fun updateWeight(weight: Weight)

    @Delete
    fun deleteWeight(weight: Weight)

    @Query("SELECT * FROM weight_table")
    fun getAllWeights(): LiveData<Map<Long, Double>>


}