package com.example.spaceapp.data

import com.example.spaceapp.data.model.local.Event
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.remote.EventDTO
import com.example.spaceapp.data.model.remote.UserSpacesDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WebService {

    @GET("/api/spaces")
    fun getSpaces(): Call<List<UserSpacesDTO>>

    @GET("/api/spaces/{code}")
    fun getSpace(@Path("code") code: String): Call<Space>

    @POST("/api/spaces")
    fun createSpace(@Body space: Space): Call<Space>

    @GET("/api/spaces/{spaceCode}/events")
    fun getEventsInSpace(@Path("spaceCode") spaceCode: String): Call<List<EventDTO>>

    @POST("/api/spaces/{spaceCode}/event")
    fun createEvent(@Body event: EventDTO, @Path("spaceCode") spaceCode: String): Call<EventDTO>

}