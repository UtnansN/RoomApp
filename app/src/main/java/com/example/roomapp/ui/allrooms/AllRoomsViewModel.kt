package com.example.roomapp.ui.allrooms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class AllRoomsViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)
    private var allRooms: LiveData<List<Room>>

    init {
        allRooms = database.roomDao().getAll()
    }

    fun getUserRooms(): LiveData<List<Room>> {
        return allRooms
    }
}