package com.example.spaceapp.data.model

data class CreateEventDTO(
    val name: String,
    val description: String?,
    val dateTime: String,
    val location: String?,
    val maxAttendees: Long
)
