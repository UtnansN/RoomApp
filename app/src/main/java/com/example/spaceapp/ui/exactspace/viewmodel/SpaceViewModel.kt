package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.data.model.LongSpaceDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private val _Long_space: MutableLiveData<Resource<LongSpaceDTO>> = MutableLiveData(Resource.loading())
    var longSpace: LiveData<Resource<LongSpaceDTO>> = _Long_space

    fun setSpaceId(spaceId: String) {
//        appRepository.enqueueApiCallAndUpdateData(_space) {
//            it.getSpaceInfo(spaceId)
//        }
    }
}