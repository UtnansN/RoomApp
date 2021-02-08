package com.example.spaceapp.data.model.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

data class EventDTO (
    val eventId: Int = 0,
    val name: String = "",
    val description: String? = "",
    val dateTime: String = "",
    val location: String? = "",
)