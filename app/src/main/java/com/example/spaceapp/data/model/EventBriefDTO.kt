package com.example.spaceapp.data.model

data class EventBriefDTO (
    val eventId: Int = 0,
    val name: String = "",
    val description: String? = "",
    val dateTime: String = "",
    val location: String? = "",
)