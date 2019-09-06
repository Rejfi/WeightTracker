package com.example.weighttracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weight::class], version = 1)
abstract class WeightDatabase : RoomDatabase() {

    abstract fun weightDao(): WeightDao

    companion object {
        private var instance: WeightDatabase? = null
        //Give Instance (Singleton) of NoteDatabase
        fun getInstance(context: Context): WeightDatabase? {
            if (instance == null) {
                synchronized(WeightDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeightDatabase::class.java,
                        "weight_table")
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .build()
                }
            }
            return instance
        }

        //Delete Singleton WeightDatabase
        fun destroyInstance() {
            instance = null
        }

    }

}