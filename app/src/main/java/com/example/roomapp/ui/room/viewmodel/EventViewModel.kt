package com.example.roomapp.ui.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Event
import com.example.roomapp.data.models.Room

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)
    private var allEvents: LiveData<List<Event>>

    init {
        allEvents = database.roomDao().getEventsForRoom(-1)
    }

    // FIXME jank roomId logic
    fun setRoomId(roomId: Int) {
        allEvents = database.roomDao().getEventsForRoom(roomId)
    }

    fun getEvents(): LiveData<List<Event>> {
        return allEvents
    }
}