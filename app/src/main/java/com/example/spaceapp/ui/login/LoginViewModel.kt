package com.example.spaceapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.LoginRepository

import com.example.spaceapp.data.model.dto.Resource
import com.example.spaceapp.auth.dto.LoginResponseDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult: MutableLiveData<Resource<LoginResponseDTO>> = MutableLiveData()
    var loginResult: LiveData<Resource<LoginResponseDTO>> = _loginResult

    fun login(username: String, password: String) {
        loginRepository.login(_loginResult, username, password)
    }

    fun invokeUserSave(userName: String, password: String) {
        if (loginResult.value == null || loginResult.value!!.data == null) {
            throw RuntimeException("Body null when it shouldn't be")
        }
        loginRepository.setLoggedInUser(userName, password, loginResult.value!!.data!!.token)
    }

}