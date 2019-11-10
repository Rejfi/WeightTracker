package com.example.weighttracker.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeightDao{

    @Insert
    fun insert(weight: Weight)

    @Update
    fun update(weight: Weight)

    @Delete
    fun delete(weight: Weight)

    @Query("SELECT * FROM weight_table ORDER BY date")
    fun getAllWeights(): LiveData<List<Weight>>


}