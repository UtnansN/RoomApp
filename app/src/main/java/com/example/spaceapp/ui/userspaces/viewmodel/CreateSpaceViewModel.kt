package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.BaseSpaceDTO
import com.example.spaceapp.data.model.CreateSpaceDTO
import com.example.spaceapp.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateSpaceViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel() {

    private val _createSpaceResponse: MutableLiveData<Resource<BaseSpaceDTO>> = MutableLiveData()
    val createSpaceResponse: LiveData<Resource<BaseSpaceDTO>> = _createSpaceResponse

    fun addRoom(space: CreateSpaceDTO) {
        appRepository.enqueueApiCallAndUpdateData(_createSpaceResponse) {
            it.createSpace(space)
        }
    }
}
