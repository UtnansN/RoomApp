package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.remote.Resource
import com.example.spaceapp.data.model.remote.UserSpacesDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AllSpacesViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private val _allSpaces: MutableLiveData<Resource<List<UserSpacesDTO>>> = MutableLiveData()
    var allSpaces: LiveData<Resource<List<UserSpacesDTO>>> = _allSpaces


    fun invokeSpaceUpdate() {
        repository.fetchUserSpaces(_allSpaces)
    }

}