package com.example.spaceapp.ui.exactspace.viewmodel

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.CreateEventDTO
import com.example.spaceapp.data.model.EventBriefDTO
import com.example.spaceapp.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {

    val name = ObservableField("")
    val description = ObservableField("")
    val time = ObservableField("")
    val date = ObservableField("")
    val location = ObservableField("")
    val maxAttendees = ObservableField<Long>(0)
    val imageURI = ObservableField<Uri>()

    val chosenDate: Calendar = Calendar.getInstance()

    var spaceCode = ""

    private val _eventBriefResource: MutableLiveData<Resource<EventBriefDTO>> = MutableLiveData()
    val eventBriefResource: LiveData<Resource<EventBriefDTO>> = _eventBriefResource

    fun submitData() {
        val event = CreateEventDTO(
            name = name.get().orEmpty(),
            description = description.get(),
            dateTime = chosenDate.toInstant().toString(),
            location = location.get(),
            maxAttendees = maxAttendees.get() ?: 0
        )

        appRepository.enqueueApiCallAndUpdateData(_eventBriefResource) {
            it.createEvent(event, spaceCode)
        }
    }
}