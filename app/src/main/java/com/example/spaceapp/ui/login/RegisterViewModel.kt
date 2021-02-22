package com.example.spaceapp.ui.login

import android.net.Uri
import android.os.FileUtils
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.auth.dto.RegisterDTO
import com.example.spaceapp.auth.dto.RegisterResponseDTO
import com.example.spaceapp.data.AppRepository
import com.example.spaceapp.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {

    private val _registerResponse = MutableLiveData<Resource<RegisterResponseDTO>>()
    val registerResponse: LiveData<Resource<RegisterResponseDTO>> = _registerResponse

    val imageURI = ObservableField<Uri>()

    val registerDTO = RegisterDTO()


    fun register() {

        val filePath = imageURI.get()?.path
//        var file: RequestBody? = null
        var imagePart: MultipartBody.Part? = null


        if (filePath != null) {
            val file = File(filePath)
            val fileBody = RequestBody.create(MediaType.parse("image/png"), File(filePath))

            imagePart = MultipartBody.Part.createFormData("imageFile", file.name, fileBody)
        }

        val map = hashMapOf(
            "email" to createBodyFromString(registerDTO.email),
            "firstName" to createBodyFromString(registerDTO.firstName),
            "lastName" to createBodyFromString(registerDTO.lastName),
            "password" to createBodyFromString(registerDTO.password)
        )

        appRepository.enqueueApiCallAndUpdateData(_registerResponse) {
            it.register(map, imagePart)
        }
    }

    private fun createBodyFromString(value: String): RequestBody {
        return RequestBody.create(MultipartBody.FORM, value)
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