package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.EventBriefDTO
import com.example.spaceapp.data.model.EventExtendedDTO
import com.example.spaceapp.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventExpandedViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {

    private var spaceCode: String = ""

    private var _eventBriefDTO = MutableLiveData<EventBriefDTO>()
    var eventBriefDTO: LiveData<EventBriefDTO> = _eventBriefDTO

    private val _eventExpandedDTO = MutableLiveData<Resource<EventExtendedDTO>>(Resource.loading())
    val eventExpandedDTO: LiveData<Resource<EventExtendedDTO>> = _eventExpandedDTO

    private val _attendanceCallState = MutableLiveData<Resource<Void>>()
    val attendanceCallState: LiveData<Resource<Void>> = _attendanceCallState

    data class PreparedUIState(
        var attendeeCountString: String = "",
        var attendButtonVisible: Boolean = false,
        var unattendButtonVisible: Boolean = false
    )

    val dataLoading: ObservableField<Boolean> = ObservableField(false)

    val preparedUiState: LiveData<PreparedUIState> = Transformations.map(eventExpandedDTO) {
        dataLoading.set(false)
        if (it.status == Resource.Status.SUCCESS) {
            val dto = it.data!!
            PreparedUIState().apply {
                attendeeCountString =
                    if (dto.maxAttendees == 0) {
                        dto.attendees?.size.toString()
                    } else {
                        dto.attendees?.size.toString() + "/" + dto.maxAttendees
                    }
                attendButtonVisible =
                    !dto.currentUserAttending && (dto.maxAttendees == 0 || (dto.attendees?.size ?: 0) < dto.maxAttendees)
                unattendButtonVisible = dto.currentUserAttending
            }
        } else {
            PreparedUIState()
        }
    }

    fun setExpandedContext(spaceCode: String, eventBriefDTO: EventBriefDTO) {
        if (spaceCode != this.spaceCode && eventBriefDTO != this.eventBriefDTO.value) {
            this.spaceCode = spaceCode
            this._eventBriefDTO.value = eventBriefDTO
            fetchAttendees()
        }
    }

    fun attendEvent() {
        _attendanceCallState.value = Resource.loading()
        dataLoading.set(true)
        val eventBriefDTO = eventBriefDTO.value
        appRepository.enqueueApiCallAndUpdateData(_attendanceCallState) {
            it.attendEvent(spaceCode, eventBriefDTO!!.eventId)
        }
    }

    fun unattendEvent() {
        _attendanceCallState.value = Resource.loading()
        dataLoading.set(true)
        val eventBriefDTO = eventBriefDTO.value
        appRepository.enqueueApiCallAndUpdateData(_attendanceCallState) {
            it.unattendEvent(spaceCode, eventBriefDTO!!.eventId)
        }
    }

    fun fetchAttendees() {
        if (eventBriefDTO.value != null) {
            val eventBriefDTO = eventBriefDTO.value
            appRepository.enqueueApiCallAndUpdateData(_eventExpandedDTO) {
                it.getEventExtendedInformation(spaceCode, eventBriefDTO!!.eventId)
            }
        }
    }

}