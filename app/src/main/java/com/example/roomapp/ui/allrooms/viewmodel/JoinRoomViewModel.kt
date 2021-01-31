package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.roomapp.data.AppDatabase

class JoinRoomViewModel(application: Application) : AndroidViewModel(application) {

    private var database: AppDatabase = AppDatabase.getInstance(application.applicationContext)

}