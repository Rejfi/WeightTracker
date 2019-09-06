package com.example.weighttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weight_table")
data class Weight(
    var weight: Double,
    var date: Long){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}