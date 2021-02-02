package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.local.Space
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AllSpacesViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    val allSpaces = MutableLiveData<List<Space>>()

    init {
        invokeSpaceUpdate()
    }

    private fun invokeSpaceUpdate() {
        val call = repository.getUpdatedSpaces()
        call.enqueue(object : Callback<List<Space>> {
            override fun onResponse(call: Call<List<Space>>, response: Response<List<Space>>) {
                val body = response.body()
                if (body != null) {
                    allSpaces.value = body
                    println("Refreshed.")
                }
            }

            override fun onFailure(call: Call<List<Space>>, t: Throwable) {
                println("Fail.")
            }

        })
    }

}