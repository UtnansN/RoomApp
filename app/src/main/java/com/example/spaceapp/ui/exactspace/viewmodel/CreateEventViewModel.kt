package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.EventBriefDTO
import com.example.spaceapp.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    val name = ObservableField("")
    val description = ObservableField("")
    val time = ObservableField("")
    val date = ObservableField("")
    val location = ObservableField("")

    val chosenDate: Calendar = Calendar.getInstance()

    private val _eventBriefResource: MutableLiveData<Resource<EventBriefDTO>> = MutableLiveData()
    val eventBriefResource: LiveData<Resource<EventBriefDTO>> = _eventBriefResource

    fun submitData(spaceCode: String) {
        val event = EventBriefDTO(
                eventId = 0,
                name = name.get().orEmpty(),
                description = description.get(),
                dateTime = chosenDate.toInstant().toString(),
                location = location.get(),
        )

        appRepository.createEvent(_eventBriefResource, event, spaceCode)
    }
}