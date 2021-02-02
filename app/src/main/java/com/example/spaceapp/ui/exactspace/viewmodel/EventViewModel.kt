package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val database: LocalDatabase): ViewModel() {

    var allEvents: LiveData<List<Event>>

    init {
        allEvents = database.spaceDao().getEventsForSpace(-1)
    }

    fun setSpaceId(roomId: Int) {
        allEvents = database.spaceDao().getEventsForSpace(roomId)
    }

}