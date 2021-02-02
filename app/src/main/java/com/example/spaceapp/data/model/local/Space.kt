package com.example.spaceapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Space (
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val spaceCode: String?
)