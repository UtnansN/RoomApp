package com.example.roomapp.data

import com.example.roomapp.data.model.local.Room
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @GET("/api/rooms")
    fun getRooms(): Call<List<Room>>

    @POST("/api/rooms")
    fun createRoom(@Body room: Room): Call<Room>

}