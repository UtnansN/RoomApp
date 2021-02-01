package com.example.roomapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomapp.data.model.local.Event
import com.example.roomapp.data.model.local.Room

@Dao
interface RoomDao {

    @Query("SELECT * FROM room WHERE id=:id")
    fun getById(id: Int): LiveData<Room>

    @Query("SELECT * FROM room ORDER BY name")
    fun getAll(): LiveData<List<Room>>

    @Query("SELECT * FROM event WHERE roomId=:roomId")
    fun getEventsForRoom(roomId: Int): LiveData<List<Event>>

    @Insert
    fun insert(room: Room)

    @Insert
    fun insertEvent(event: Event)

}