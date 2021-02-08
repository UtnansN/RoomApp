package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.remote.Resource
import com.example.spaceapp.data.model.remote.SpaceInfoDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private val _space: MutableLiveData<Resource<SpaceInfoDTO>> = MutableLiveData(Resource.loading())
    var space: LiveData<Resource<SpaceInfoDTO>> = _space

    fun setSpaceId(spaceId: String) {
//        appRepository.enqueueApiCallAndUpdateData(_space) {
//            it.getSpaceInfo(spaceId)
//        }
    }
}