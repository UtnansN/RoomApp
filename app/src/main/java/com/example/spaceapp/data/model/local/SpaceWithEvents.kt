package com.example.spaceapp.data.model.local

import androidx.room.Embedded
import androidx.room.Relation

data class SpaceWithEvents(
    @Embedded val space: Space,
    @Relation(
        parentColumn = "id",
        entityColumn = "roomId"
    )
    val events: List<Event>
)
