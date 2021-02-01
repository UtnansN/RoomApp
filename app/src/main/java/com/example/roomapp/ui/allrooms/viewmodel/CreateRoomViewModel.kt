package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Room
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateRoomViewModel @Inject constructor(private val database: LocalDatabase): ViewModel() {

//    private var database: LocalDatabase = LocalDatabase.getInstance(application.applicationContext)

    fun addRoom(room: Room) {
        database.roomDao().insert(room)
    }
}