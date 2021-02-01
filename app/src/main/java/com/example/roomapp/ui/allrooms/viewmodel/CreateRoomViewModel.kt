package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.AppRepository
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Room
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CreateRoomViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val database: LocalDatabase
) : ViewModel() {

    fun addRoom(room: Room) {
        database.roomDao().insert(room)
        val call = appRepository.createRoom(room)

        call.enqueue(object : Callback<Room> {
            override fun onResponse(call: Call<Room>, response: Response<Room>) {
                // TODO
            }

            override fun onFailure(call: Call<Room>, t: Throwable) {
                println("failed to add room")
            }

        })
    }
}
