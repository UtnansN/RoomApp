package com.example.spaceapp.data

import androidx.lifecycle.MutableLiveData
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.remote.EventDTO
import com.example.spaceapp.data.model.remote.Resource
import com.example.spaceapp.data.model.remote.UserSpacesDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val webService: WebService,
    private val credentialCache: CredentialCache
) {

    fun createEvent(holder: MutableLiveData<Resource<EventDTO>>, event: EventDTO, spaceCode: String) {
        val call = webService.createEvent(event, spaceCode)
        call.enqueue(genericDataUpdaterCallback(holder))
    }

    fun <T> enqueueApiCallAndUpdateData(holder: MutableLiveData<Resource<T>>, webCall: (webService: WebService) -> Call<T>) {
        webCall(webService).enqueue(genericDataUpdaterCallback(holder))
    }

    private fun <T> genericDataUpdaterCallback(holder: MutableLiveData<Resource<T>>): Callback<T> {

        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (response.isSuccessful) {
                    holder.value = Resource.success(body)
                } else {
                    if (response.code() == 401) {
                        credentialCache.invalidateUser()
                    }
                    holder.value = Resource.error(response.message())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                holder.value = Resource.error(t.message.orEmpty())
            }
        }
    }

}