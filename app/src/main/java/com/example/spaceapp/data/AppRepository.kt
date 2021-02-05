package com.example.spaceapp.data

import androidx.lifecycle.LiveData
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

    fun createSpace(holder: MutableLiveData<Resource<Space>>, space: Space) {
        val call = webService.createSpace(space)
        call.enqueue(genericDataUpdaterCallback(holder))
    }

    fun fetchUserSpaces(holder: MutableLiveData<Resource<List<UserSpacesDTO>>>) {
        val call = webService.getSpaces()
        call.enqueue(genericDataUpdaterCallback(holder))
    }

    fun fetchSpace(holder: MutableLiveData<Resource<Space>>, code: String) {
        val call = webService.getSpace(code)
        call.enqueue(genericDataUpdaterCallback(holder))
    }

    fun joinSpace(holder: MutableLiveData<Resource<Void>>, code: String) {
        val call = webService.joinSpace(code)
        call.enqueue(genericDataUpdaterCallback(holder))
    }

    fun fetchEventsInSpace(holder: MutableLiveData<Resource<List<EventDTO>>>, code: String) {
        val call = webService.getEventsInSpace(code)
        call.enqueue(genericDataUpdaterCallback(holder))
    }

    fun createEvent(holder: MutableLiveData<Resource<EventDTO>>, event: EventDTO, spaceCode: String) {
        val call = webService.createEvent(event, spaceCode)
        call.enqueue(genericDataUpdaterCallback(holder))
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