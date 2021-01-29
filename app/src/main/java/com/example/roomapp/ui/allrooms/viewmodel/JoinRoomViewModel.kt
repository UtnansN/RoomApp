package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class JoinRoomViewModel(application: Application) : AndroidViewModel(application) {

    private var database: AppDatabase = AppDatabase.getInstance(application.applicationContext)

}