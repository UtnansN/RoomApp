package com.example.spaceapp.data.model.remote

data class UserSpacesDTO(

    val code: String,

    val name: String,

    val nextEvent: EventBrief
)

data class EventBrief(
    val nextEventName: String,
    val nextEventDate: String
)