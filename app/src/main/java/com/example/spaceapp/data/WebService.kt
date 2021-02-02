package com.example.spaceapp.data

import com.example.spaceapp.data.model.local.Space
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @GET("/api/spaces")
    fun getSpaces(): Call<List<Space>>

    @POST("/api/spaces")
    fun createSpace(@Body space: Space): Call<Space>

}