package com.example.roomapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomapp.data.models.Room

@Dao
interface RoomDao {

    @Query("SELECT * FROM room ORDER BY name")
    fun getAll(): LiveData<List<Room>>

    @Insert
    fun insert(room: Room)

}