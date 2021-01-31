package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.model.Room

class CreateRoomViewModel(application: Application) : AndroidViewModel(application) {

    private var database: AppDatabase = AppDatabase.getInstance(application.applicationContext)

    fun addRoom(room: Room) {
        database.roomDao().insert(room)
    }
}