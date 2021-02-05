package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CreateSpaceViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel() {

    private val _createSpaceStatus: MutableLiveData<Resource<Space>> = MutableLiveData()
    val createSpaceStatus: LiveData<Resource<Space>> = _createSpaceStatus

    fun addRoom(space: Space) {
        appRepository.createSpace(_createSpaceStatus, space)
    }
}
