package com.example.spaceapp.data.model.dto

data class EventDTO (
    val eventId: Int = 0,
    val name: String = "",
    val description: String? = "",
    val dateTime: String = "",
    val location: String? = "",
)