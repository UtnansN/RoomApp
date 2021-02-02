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
    private val localDatabase: LocalDatabase
) {

    fun createSpace(space: Space): Call<Space> {
        return webService.createSpace(space)
    }

    fun fetchUserSpaces(): LiveData<Resource<List<UserSpacesDTO>>> {

        val output = getResourceObservable<List<UserSpacesDTO>>()

        val call = webService.getSpaces()
        call.enqueue(genericCallback(output))
        return output
    }

    fun fetchSpace(code: String): LiveData<Resource<Space>> {
        val output = getResourceObservable<Space>()

        val call = webService.getSpace(code)
        call.enqueue(genericCallback(output))
        return output
    }

    fun fetchEventsInSpace(code: String): LiveData<Resource<List<EventDTO>>> {
        val output = getResourceObservable<List<EventDTO>>()

        val call = webService.getEventsInSpace(code)
        call.enqueue(genericCallback(output))
        return output
    }

    fun createEvent(event: EventDTO, spaceCode: String): LiveData<Resource<EventDTO>> {
        val output = getResourceObservable<EventDTO>()

        val call = webService.createEvent(event, spaceCode)
        call.enqueue(genericCallback(output))
        return output
    }

    private fun <T> genericCallback(output: MutableLiveData<Resource<T>>): Callback<T> {

        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    output.value = Resource.success(body)
                }
                else {
                    output.value = Resource.error(response.message())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                output.value = Resource.error(t.message.orEmpty())
            }
        }
    }

    private fun <T> getResourceObservable(): MutableLiveData<Resource<T>> {
        return MutableLiveData<Resource<T>>(Resource.loading())
    }

}