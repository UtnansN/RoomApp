package com.example.roomapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String
)

