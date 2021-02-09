package com.example.spaceapp.data.model.dto

data class EventPackageDTO(
    val events: List<EventDTO>,

    val hasEventCreateRights: Boolean
)
