package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Event
import com.example.spaceapp.data.model.remote.EventDTO
import com.example.spaceapp.data.model.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private var spaceCode: String? = null

    private var _allEvents: MutableLiveData<Resource<List<EventDTO>>> = MutableLiveData(Resource.loading())
    var allEvents: LiveData<Resource<List<EventDTO>>> = _allEvents

    fun setSpaceCode(code: String) {
        if (spaceCode == null || spaceCode != code) {
            spaceCode = code
            refreshEvents()
        }
    }

    private fun refreshEvents() {
        if (spaceCode != null) {
            appRepository.fetchEventsInSpace(_allEvents, spaceCode!!)
        }
    }

}