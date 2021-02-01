package com.example.roomapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Event (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String?,
    val time: String?,
    val location: String?,
    val roomId: Int
)