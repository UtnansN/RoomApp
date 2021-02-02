package com.example.spaceapp.ui.userspaces.viewmodel

import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinSpaceViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {



}