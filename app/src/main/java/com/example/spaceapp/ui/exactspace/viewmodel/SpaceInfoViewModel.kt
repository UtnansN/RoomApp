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
class SpaceInfoViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _longSpace: MutableLiveData<Resource<LongSpaceDTO>> = MutableLiveData()
    val longSpace: LiveData<Resource<LongSpaceDTO>> = _longSpace

    private val _leaveResponse: MutableLiveData<Resource<Void>> = MutableLiveData()
    val leaveResponse: LiveData<Resource<Void>> = _leaveResponse

    var spaceCode = ""

    fun leaveSpace() {
        appRepository.enqueueApiCallAndUpdateData(_leaveResponse) {
            it.leaveSpace(spaceCode)
        }
    }

    fun getSpaceInfo() {
        appRepository.enqueueApiCallAndUpdateData(_longSpace) {
            it.getSpaceInfo(spaceCode)
        }
    }

}