package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Space
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CreateSpaceViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val database: LocalDatabase
) : ViewModel() {

    fun addRoom(space: Space) {
        val call = appRepository.createSpace(space)

        call.enqueue(object : Callback<Space> {
            override fun onResponse(call: Call<Space>, response: Response<Space>) {
                // TODO
            }

            override fun onFailure(call: Call<Space>, t: Throwable) {
                println("failed to add room")
            }

        })
    }
}
