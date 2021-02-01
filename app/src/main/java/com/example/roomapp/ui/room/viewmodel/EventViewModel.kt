package com.example.roomapp.ui.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val database: LocalDatabase): ViewModel() {

    var allEvents: LiveData<List<Event>>

    init {
        allEvents = database.roomDao().getEventsForRoom(-1)
    }

    fun setRoomId(roomId: Int) {
        allEvents = database.roomDao().getEventsForRoom(roomId)
    }

}