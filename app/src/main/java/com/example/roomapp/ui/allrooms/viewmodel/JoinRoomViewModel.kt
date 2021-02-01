package com.example.roomapp.ui.allrooms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.roomapp.data.AppRepository
import com.example.roomapp.data.LocalDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinRoomViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {



}