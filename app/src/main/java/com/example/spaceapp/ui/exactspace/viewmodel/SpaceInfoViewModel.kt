package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.dto.Resource
import com.example.spaceapp.data.model.dto.SpaceInfoDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceInfoViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _spaceInfo: MutableLiveData<Resource<SpaceInfoDTO>> = MutableLiveData()
    val spaceInfo: LiveData<Resource<SpaceInfoDTO>> = _spaceInfo

    fun getSpaceInfo(spaceCode: String) {
        appRepository.enqueueApiCallAndUpdateData(_spaceInfo) {
            it.getSpaceInfo(spaceCode)
        }
    }

}