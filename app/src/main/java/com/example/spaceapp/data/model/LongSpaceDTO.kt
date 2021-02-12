package com.example.spaceapp.data.model

data class LongSpaceDTO(

    val code: String,

    val name: String,

    val description: String,

    val members: List<UserBriefDTO>,
)