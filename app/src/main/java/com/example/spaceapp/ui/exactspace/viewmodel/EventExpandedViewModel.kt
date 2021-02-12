package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.data.model.UserBriefDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventExpandedViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _attendees = MutableLiveData<Resource<List<UserBriefDTO>>>()
    val attendees: LiveData<Resource<List<UserBriefDTO>>> = _attendees

    lateinit var spaceCode: String
    var eventId: Long = -1

    fun fetchAttendees() {
        appRepository.enqueueApiCallAndUpdateData(_attendees) {
            it.getEventAttendees(spaceCode, eventId)
        }
    }

}