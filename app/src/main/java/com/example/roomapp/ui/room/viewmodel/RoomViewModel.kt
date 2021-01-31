package com.example.roomapp.ui.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.model.Room

class RoomViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)

    fun getRoomData(roomId: Int): LiveData<Room> {
        return database.roomDao().getById(roomId)
    }
}