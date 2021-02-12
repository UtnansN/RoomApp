package com.example.spaceapp.data.model

data class ShortSpaceDTO(
    val code: String,

    val name: String,

    val nextEvent: NextEventBrief
)

data class NextEventBrief(
    val nextEventName: String,
    val nextEventDate: String
)