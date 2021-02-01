package com.example.roomapp.ui.room.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.LocalDatabase
import com.example.roomapp.data.model.local.Event
import dagger.hilt.android.AndroidEntryPoint
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
                roomId = roomId
        )

        database.roomDao().insertEvent(event)
    }

}