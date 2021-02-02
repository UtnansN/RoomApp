package com.example.spaceapp.ui.exactspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.LocalDatabase
import com.example.spaceapp.data.model.local.Space
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceViewModel @Inject constructor(private val database: LocalDatabase): ViewModel() {

    var space: LiveData<Space> = database.spaceDao().getById(-1)

    fun setSpaceId(spaceId: Int) {
        space = database.spaceDao().getById(spaceId)
    }
}