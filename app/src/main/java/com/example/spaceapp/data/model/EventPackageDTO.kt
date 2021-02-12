package com.example.spaceapp.data.model

data class EventPackageDTO(
    val events: List<EventBriefDTO>,

    val hasEventCreateRights: Boolean
)
