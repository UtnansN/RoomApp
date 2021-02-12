package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.BaseSpaceDTO
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.data.model.ShortSpaceDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinSpaceViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    private val _joinSpaceResponse: MutableLiveData<Resource<BaseSpaceDTO>> = MutableLiveData()
    val joinSpaceResponse: LiveData<Resource<BaseSpaceDTO>> = _joinSpaceResponse

    fun joinSpace(code: String) {
        repository.enqueueApiCallAndUpdateData(_joinSpaceResponse) {
            it.joinSpace(code)
        }
    }

}