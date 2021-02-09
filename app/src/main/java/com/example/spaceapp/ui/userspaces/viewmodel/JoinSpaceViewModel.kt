package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.dto.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinSpaceViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    private val _joinSpaceResponse: MutableLiveData<Resource<Void>> = MutableLiveData()
    val joinSpaceResponse: LiveData<Resource<Void>> = _joinSpaceResponse

    fun joinSpace(code: String) {
        repository.enqueueApiCallAndUpdateData(_joinSpaceResponse) {
            it.joinSpace(code)
        }
    }

}