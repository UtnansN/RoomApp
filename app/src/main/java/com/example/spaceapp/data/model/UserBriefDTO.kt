package com.example.spaceapp.data.model

data class UserBriefDTO(
    val id: Long,

    val fullName: String,

    val joinDate: String?,

    val role: String?,

    val imageURI: String?
)
