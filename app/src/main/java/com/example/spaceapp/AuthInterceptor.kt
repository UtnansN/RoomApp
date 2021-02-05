package com.example.spaceapp

import com.example.spaceapp.data.CredentialCache
import com.example.spaceapp.data.model.remote.auth.LoginResponseDTO
import com.google.gson.Gson
import okhttp3.*
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val credentialCache: CredentialCache): Interceptor {

    private val JSON = MediaType.parse("application/json; charset=utf-8")
    private val mapper = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        if (credentialCache.isTokenExpiredOrNull() && !isAuthRelated(original.url().encodedPath())) {
            val loginDTO = credentialCache.getLoginDTO()

            val refreshBody = RequestBody.create(JSON, mapper.toJson(loginDTO))
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
        val resumedCall = original.newBuilder()
            .addHeader("Authorization",  "Bearer " + credentialCache.getCurrentToken())
            .build()
        return chain.proceed(resumedCall)
    }

    private fun isAuthRelated(path: String): Boolean {
        return path == Constants.DEST_LOGIN || path == Constants.DEST_REGISTER
    }
}