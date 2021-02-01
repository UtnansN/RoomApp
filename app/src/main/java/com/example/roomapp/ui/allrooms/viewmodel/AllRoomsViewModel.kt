package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Room
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllRoomsViewModel @Inject constructor(private val database: LocalDatabase) : ViewModel() {

    private var allRooms: LiveData<List<Room>> = database.roomDao().getAll()

    fun getUserRooms(): LiveData<List<Room>> {
        return allRooms
    }
}