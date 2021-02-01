package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.AppRepository
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Room
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AllRoomsViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

//    var allRooms: LiveData<List<Room>> = repository.getLocalRooms()

    val allRooms = MutableLiveData<List<Room>>()

    init {
        invokeRoomUpdate()
    }

    private fun invokeRoomUpdate() {
        val call = repository.getUpdatedRooms()
        call.enqueue(object : Callback<List<Room>> {
            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                val body = response.body()
                if (body != null) {
//                    repository.refreshRooms(body)
                    allRooms.value = body
                    println("Refreshed.")
                }
            }

            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                println("Fail.")
            }

        })
    }

}