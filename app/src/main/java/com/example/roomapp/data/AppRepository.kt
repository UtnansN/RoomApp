package com.example.roomapp.data

import com.example.roomapp.data.model.local.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(private val webService: WebService,
                                        private val localDatabase: LocalDatabase) {

    suspend fun createRoom(room: Room) {
        webService.createRoom(room)
    }

    suspend fun refreshRooms() {
        withContext(Dispatchers.IO) {

        }
    }

}