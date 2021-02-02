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

    private var spaceCode = MutableLiveData<String>(null)
    var allEvents: LiveData<Resource<List<EventDTO>>> = Transformations.switchMap(spaceCode) {
        println("Called transformation!")
        appRepository.fetchEventsInSpace(it)
    }

    fun setSpaceCode(code: String) {
        println("Called set!")
        if (spaceCode.value != code) {
            spaceCode.value = code
        }
    }

    private fun refreshEvents(code: String) {
        allEvents = appRepository.fetchEventsInSpace(code)
    }

}