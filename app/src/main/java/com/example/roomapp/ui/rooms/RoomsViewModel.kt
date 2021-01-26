package com.example.roomapp.ui.rooms

import android.app.Application
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class RoomsViewModel(application: Application) : AndroidViewModel(application) {

    private var database: AppDatabase = AppDatabase.getInstance(application.applicationContext)
    private var allRooms: LiveData<List<Room>>

    init {
        allRooms = database.roomDao().getAll()
    }

    fun getUserRooms(): LiveData<List<Room>> {
        return allRooms
    }

    fun addRoom() {
        database.roomDao().insert(Room(id = 0, name = "AAAA", description = "ASDF"))
    }
}