package com.example.spaceapp.data.model.remote

data class SpaceInfoDTO(

    val code: String,

    val name: String,

    val description: String,

    val members: List<MemberDTO>,
)

data class MemberDTO(

    val id: Long,

    val name: String
)