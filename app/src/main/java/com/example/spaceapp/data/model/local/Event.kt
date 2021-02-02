package com.example.spaceapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String?,
    val time: String?,
    val location: String?,
    val spaceId: Int
)