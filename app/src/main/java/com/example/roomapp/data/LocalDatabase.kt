package com.example.roomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.data.dao.RoomDao
import com.example.roomapp.data.model.local.Event
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import com.example.roomapp.data.model.local.Room as RoomEntity

@Database(entities = [RoomEntity::class, Event::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}