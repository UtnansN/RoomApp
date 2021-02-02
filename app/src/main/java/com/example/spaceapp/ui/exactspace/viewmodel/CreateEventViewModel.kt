package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(private val database: LocalDatabase): ViewModel() {

    val name = ObservableField("")
    val description = ObservableField("")
    val time = ObservableField("")
    val date = ObservableField("")
    val location = ObservableField("")

    fun submitData(roomId: Int) {
        val event = Event(
                id = 0,
                name = name.get().orEmpty(),
                description = description.get(),
                time = time.get(),
                location = location.get(),
                spaceId = roomId
        )

        database.spaceDao().insertEvent(event)
    }

}