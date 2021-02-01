package com.example.roomapp.data.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.data.model.local.Event
import com.example.roomapp.data.model.local.Room

data class RoomWithEvents(
    @Embedded val room: Room,
    @Relation(
        parentColumn = "id",
        entityColumn = "roomId"
    )
    val events: List<Event>
)
