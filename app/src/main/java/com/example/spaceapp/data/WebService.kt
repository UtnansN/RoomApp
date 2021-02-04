package com.example.spaceapp.data

import com.example.spaceapp.Constants
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.remote.EventDTO
import com.example.spaceapp.data.model.remote.auth.LoginDTO
import com.example.spaceapp.data.model.remote.auth.RegisterDTO
import com.example.spaceapp.data.model.remote.UserSpacesDTO
import com.example.spaceapp.data.model.remote.auth.LoginResponseDTO
import com.example.spaceapp.data.model.remote.auth.RegisterResponseDTO
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

    @POST("/api/spaces/{spaceCode}/join")
    fun joinSpace(@Path("spaceCode") spaceCode: String): Call<Void>

    @GET("/api/spaces/{spaceCode}/events")
    fun getEventsInSpace(@Path("spaceCode") spaceCode: String): Call<List<EventDTO>>

    @POST("/api/spaces/{spaceCode}/event")
    fun createEvent(@Body event: EventDTO, @Path("spaceCode") spaceCode: String): Call<EventDTO>

    @POST(Constants.DEST_LOGIN)
    fun login(@Body loginDTO: LoginDTO): Call<LoginResponseDTO>

    @POST("/api/signup")
    fun signup(@Body registerDTO: RegisterDTO): Call<RegisterResponseDTO>

}