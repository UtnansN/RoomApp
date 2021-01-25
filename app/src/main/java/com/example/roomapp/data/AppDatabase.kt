package com.example.roomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.data.dao.RoomDao
import com.example.roomapp.data.models.Room as RoomEntity

@Database(entities = [RoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {

            synchronized(this) {

                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext, AppDatabase::class.java, "rooms.db")
                            .allowMainThreadQueries()
                            .build()
                }

            }

            return INSTANCE as AppDatabase
        }

        fun deleteInstance() {
            INSTANCE = null
        }
    }

}