package com.example.spaceapp.data.model

data class EventExtendedDTO (

    val currentUserAttending: Boolean,

    val maxAttendees: Int,

    val attendees: List<UserBriefDTO>?,

    val comments: List<EventCommentDTO>?
)