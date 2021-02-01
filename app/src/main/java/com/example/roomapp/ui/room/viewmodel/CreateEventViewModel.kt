package com.example.roomapp.ui.room.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.model.Event

class CreateEventViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application.applicationContext)

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