package com.example.spaceapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spaceapp.data.dao.SpaceDao
import com.example.spaceapp.data.model.local.Event
import com.example.spaceapp.data.model.local.Space

@Database(entities = [Space::class, Event::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun spaceDao(): SpaceDao
}