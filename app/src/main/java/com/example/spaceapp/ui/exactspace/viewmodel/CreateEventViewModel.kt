package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.remote.EventDTO
import com.example.spaceapp.data.model.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    val name = ObservableField("")
    val description = ObservableField("")
    val time = ObservableField("")
    val date = ObservableField("")
    val location = ObservableField("")

    private val _eventResource: MutableLiveData<Resource<EventDTO>> = MutableLiveData()
    val eventResource: LiveData<Resource<EventDTO>> = _eventResource

    fun submitData(spaceCode: String) {
        val event = EventDTO(
                eventId = 0,
                name = name.get().orEmpty(),
                description = description.get(),
                dateTime = "",
                location = location.get(),
        )

        appRepository.createEvent(_eventResource, event, spaceCode)
    }

    private fun convertToDateTimeString(date: String, time: String): Instant {
        return Instant.now()
    }

}