package com.example.spaceapp.data

import androidx.lifecycle.MutableLiveData
import com.example.spaceapp.auth.dto.LoginDTO
import com.example.spaceapp.data.model.remote.Resource
import com.example.spaceapp.auth.dto.LoginResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val webService: WebService,
    private val credentialCache: CredentialCache
) {

    fun login(
        holder: MutableLiveData<Resource<LoginResponseDTO>>,
        email: String,
        password: String
    ) {
        holder.value = Resource.loading()

        val call = webService.login(LoginDTO(email, password))
        call.enqueue(object : Callback<LoginResponseDTO> {
            override fun onResponse(
                call: Call<LoginResponseDTO>,
                response: Response<LoginResponseDTO>
            ) {
                if (response.body() != null) {
                    holder.value = Resource.success(response.body()!!)
                } else {
                    holder.value = Resource.error(response.code().toString())
                }
            }

            override fun onFailure(call: Call<LoginResponseDTO>, t: Throwable) {
                holder.value = Resource.error(t.message.orEmpty())
            }
        })
    }

    fun setLoggedInUser(userName: String, password: String, token: String) {
        credentialCache.setLoggedInUser(userName, password, token)
    }

}