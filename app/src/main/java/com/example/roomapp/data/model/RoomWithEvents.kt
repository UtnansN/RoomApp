package com.example.roomapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class RoomWithEvents(
    @Embedded val room: Room,
    @Relation(
        parentColumn = "id",
        entityColumn = "roomId"
    )
    val events: List<Event>
)
