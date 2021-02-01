package com.example.roomapp.data

import androidx.lifecycle.LiveData
import com.example.roomapp.data.model.local.Room
import retrofit2.Call
import javax.inject.Inject

class AppRepository @Inject constructor(private val webService: WebService,
                                        private val localDatabase: LocalDatabase) {

    fun createRoom(room: Room): Call<Room> {
        return webService.createRoom(room)
    }

    fun getLocalRooms(): LiveData<List<Room>> {
        return localDatabase.roomDao().getAll()
    }

    fun getUpdatedRooms(): Call<List<Room>> {
        return webService.getRooms()
    }

    fun refreshRooms(rooms: List<Room>) {
        localDatabase.roomDao().insertAll(rooms)
    }

}