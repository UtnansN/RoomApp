package com.example.roomapp.ui.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class RoomViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)

    private val _roomName = MutableLiveData<String>().apply {
        value = "Room name"
    }
    val roomName: LiveData<String> = _roomName

    fun setupRoom(roomId: Int) {
        // FIXME: Observing the database entry. Maybe data binding?
//        val room: Room = database.roomDao().getById(roomId).value
//
//        room.observe(this, Observer {
//            _roomName.value = it.name
//        })

    }

}