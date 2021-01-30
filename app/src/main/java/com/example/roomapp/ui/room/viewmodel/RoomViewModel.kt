package com.example.roomapp.ui.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class RoomViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)

    private val _roomName = MutableLiveData<String>().apply {
        value = "Room name"
    }
    val roomName: LiveData<String> = _roomName

    // FIXME
    fun setRoomId(roomId: Int) {
        val room: Room = database.roomDao().getById(roomId)
        _roomName.value = room.name
        //_roomName.value = database.roomDao().getById(roomId).value?.name
    }
}