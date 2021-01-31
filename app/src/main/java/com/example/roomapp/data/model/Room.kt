package com.example.roomapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Room (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String?,
    val roomCode: String?
)