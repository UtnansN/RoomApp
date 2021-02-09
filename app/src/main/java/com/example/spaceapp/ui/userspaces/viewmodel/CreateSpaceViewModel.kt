package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.data.model.dto.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateSpaceViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel() {

    private val _createSpaceStatus: MutableLiveData<Resource<Space>> = MutableLiveData()
    val createSpaceStatus: LiveData<Resource<Space>> = _createSpaceStatus

    fun addRoom(space: Space) {
        appRepository.enqueueApiCallAndUpdateData(_createSpaceStatus) {
            it.createSpace(space)
        }
    }
}
