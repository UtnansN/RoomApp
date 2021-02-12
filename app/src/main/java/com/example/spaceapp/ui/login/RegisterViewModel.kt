package com.example.spaceapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.auth.dto.RegisterDTO
import com.example.spaceapp.auth.dto.RegisterResponseDTO
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private val _registerResponse = MutableLiveData<Resource<RegisterResponseDTO>>()
    val registerResponse: LiveData<Resource<RegisterResponseDTO>> = _registerResponse

    val registerDTO = RegisterDTO()

    fun register() {
        appRepository.enqueueApiCallAndUpdateData(_registerResponse) {
            it.register(registerDTO)
        }
    }

    // A placeholder username validation check
    private fun isEmailValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

}