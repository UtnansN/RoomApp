package com.example.spaceapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spaceapp.data.model.local.Event
import com.example.spaceapp.data.model.local.Space

@Dao
interface SpaceDao {

    @Query("SELECT * FROM space WHERE id=:id")
    fun getById(id: Int): LiveData<Space>

    @Query("SELECT * FROM space ORDER BY name")
    fun getAll(): LiveData<List<Space>>

    @Query("SELECT * FROM event WHERE spaceId=:spaceId")
    fun getEventsForSpace(spaceId: Int): LiveData<List<Event>>

    @Insert
    fun insert(space: Space)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(spaces: List<Space>)

    @Insert
    fun insertEvent(event: Event)

}