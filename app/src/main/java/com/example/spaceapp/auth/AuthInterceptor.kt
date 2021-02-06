package com.example.spaceapp.auth

import com.example.spaceapp.Constants
import com.example.spaceapp.data.CredentialCache
import com.example.spaceapp.auth.dto.LoginResponseDTO
import com.google.gson.Gson
import okhttp3.*
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val credentialCache: CredentialCache): Interceptor {

    private val json = MediaType.parse("application/json; charset=utf-8")
    private val mapper = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        if (credentialCache.isTokenExpiredOrNull() && !isAuthRelated(original.url().encodedPath())) {
            val loginDTO = credentialCache.getLoginDTO()

            val refreshBody = RequestBody.create(json, mapper.toJson(loginDTO))
            val refreshRequest = original.newBuilder()
                .post(refreshBody)
                .url(Constants.API_URL + Constants.DEST_LOGIN)
                .build()

            val response = chain.proceed(refreshRequest)
            if (!response.isSuccessful) {
                return response
            }
            val loginResponseDTO = mapper.fromJson(response.body()?.string(), LoginResponseDTO::class.java)
            credentialCache.setCurrentToken(loginResponseDTO.token)
        }

        return if (isAuthRelated(original.url().encodedPath())) {
            chain.proceed(original)
        } else {
            val resumedCall = original.newBuilder()
                .addHeader("Authorization",  "Bearer " + credentialCache.getCurrentToken())
                .build()

            chain.proceed(resumedCall)
        }

    }

    private fun isAuthRelated(path: String): Boolean {
        return path == Constants.DEST_LOGIN || path == Constants.DEST_REGISTER
    }
}