package com.example.spaceapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.auth.dto.LoginDTO

import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.auth.dto.LoginResponseDTO
import com.example.spaceapp.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private val _loginResult: MutableLiveData<Resource<LoginResponseDTO>> = MutableLiveData()
    var loginResult: LiveData<Resource<LoginResponseDTO>> = _loginResult

    var loginDTO = LoginDTO()

    fun login() {
        appRepository.enqueueApiCallAndUpdateData(_loginResult) {
            it.login(loginDTO)
        }
    }

    fun invokeUserSave() {
        if (loginResult.value == null || loginResult.value!!.data == null) {
            throw RuntimeException("Body null when it shouldn't be")
        }
        appRepository.setLoggedInUser(loginDTO.email, loginDTO.password, loginResult.value!!.data!!.token)
    }

}