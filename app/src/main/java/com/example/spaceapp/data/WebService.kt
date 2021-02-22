package com.example.spaceapp.data

import com.example.spaceapp.utils.Constants
import com.example.spaceapp.auth.dto.LoginDTO
import com.example.spaceapp.auth.dto.RegisterDTO
import com.example.spaceapp.auth.dto.LoginResponseDTO
import com.example.spaceapp.auth.dto.RegisterResponseDTO
import com.example.spaceapp.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @GET("/api/spaces")
    fun getSpaces(): Call<List<ShortSpaceDTO>>

    @GET("/api/spaces/{code}")
    fun getSpaceInfo(@Path("code") code: String): Call<LongSpaceDTO>

    @POST("/api/spaces")
    fun createSpace(@Body space: CreateSpaceDTO): Call<BaseSpaceDTO>

    @PUT("/api/spaces/{spaceCode}/join")
    fun joinSpace(@Path("spaceCode") spaceCode: String): Call<BaseSpaceDTO>

    @PUT("/api/spaces/{spaceCode}/leave")
    fun leaveSpace(@Path("spaceCode") spaceCode: String): Call<Void>

    @GET("/api/spaces/{spaceCode}/events")
    fun getEventsInSpace(@Path("spaceCode") spaceCode: String): Call<EventPackageDTO>

    @POST("/api/spaces/{spaceCode}/events")
    fun createEvent(@Body createEventDTO: CreateEventDTO, @Path("spaceCode") spaceCode: String): Call<EventBriefDTO>

    @POST(Constants.DEST_LOGIN)
    fun login(@Body loginDTO: LoginDTO): Call<LoginResponseDTO>

    @Multipart
    @POST(Constants.DEST_REGISTER)
    fun register(@PartMap partMap: HashMap<String, RequestBody>,
                 @Part file: MultipartBody.Part?): Call<RegisterResponseDTO>

    @GET("/api/spaces/{spaceCode}/events/{eventId}")
    fun getEventExtendedInformation(@Path("spaceCode") spaceCode: String,
                                    @Path("eventId") eventId: Long): Call<EventExtendedDTO>

    @PUT("/api/spaces/{spaceCode}/events/{eventId}/attend")
    fun attendEvent(@Path("spaceCode") spaceCode: String,
                         @Path("eventId") eventId: Long): Call<Void>

    @PUT("/api/spaces/{spaceCode}/events/{eventId}/unattend")
    fun unattendEvent(@Path("spaceCode") spaceCode: String,
                    @Path("eventId") eventId: Long): Call<Void>

}