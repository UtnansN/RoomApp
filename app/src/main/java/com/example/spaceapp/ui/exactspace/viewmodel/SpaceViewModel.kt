package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    var space: LiveData<Resource<Space>> = MutableLiveData()

    fun setSpaceId(spaceId: String) {
        space = appRepository.fetchSpace(spaceId)
    }
}