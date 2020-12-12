package com.example.simulacion02dlassallec.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Housy::class, HousyDetails::class], version = 1)
abstract class HousyDataBase: RoomDatabase() {

    abstract fun housyDao():HousyDao

    companion object {

        @Volatile
        private var INSTANCE: HousyDataBase? = null

        fun getDatabase(context: Context): HousyDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HousyDataBase::class.java,
                    "housy_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}