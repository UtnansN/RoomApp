package com.example.spaceapp.data

import android.content.SharedPreferences
import com.auth0.android.jwt.JWT
import com.example.spaceapp.utils.Constants
import com.example.spaceapp.auth.dto.LoginDTO
import javax.inject.Inject

class CredentialCache @Inject constructor(@LoginPreferences private val loginPreferences: SharedPreferences) {

    fun setLoggedInUser(userName: String, password: String, token: String) {
        val editor = loginPreferences.edit()
        editor.putString(Constants.PREF_USERNAME, userName)
        editor.putString(Constants.PREF_PASSWORD, password)
        editor.putString(Constants.PREF_TOKEN, token)
        editor.apply()
    }

    fun setCurrentToken(token: String) {
        val editor = loginPreferences.edit()
        editor.putString(Constants.PREF_TOKEN, token)
        editor.apply()
    }

    fun getCurrentToken(): String? {
        return loginPreferences.getString(Constants.PREF_TOKEN, null)
    }

    fun isTokenExpiredOrNull(): Boolean {
        val token = getCurrentToken() ?: return true

        val jwt = JWT(token)
        return jwt.isExpired(0)
    }

    fun getLoginDTO(): LoginDTO {
        val login = LoginDTO()
        login.email = loginPreferences.getString(Constants.PREF_USERNAME, null)!!
        login.password = loginPreferences.getString(Constants.PREF_PASSWORD, null)!!

        return login
    }

    fun invalidateUser() {
        val editor = loginPreferences.edit()
        editor.remove(Constants.PREF_USERNAME)
        editor.remove(Constants.PREF_PASSWORD)
        editor.remove(Constants.PREF_TOKEN)
        editor.apply()
    }

}