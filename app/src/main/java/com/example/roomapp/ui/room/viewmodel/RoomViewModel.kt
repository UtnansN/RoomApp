package com.example.roomapp.ui.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Room
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val database: LocalDatabase): ViewModel() {

    var room: LiveData<Room> = database.roomDao().getById(-1)

    fun setRoomId(roomId: Int) {
        room = database.roomDao().getById(roomId)
    }
}