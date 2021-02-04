package com.example.spaceapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.spaceapp.ui.MainActivity
import com.example.spaceapp.ui.login.LoginActivity

class InitializeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityIntent: Intent

        val sp = getSharedPreferences(Constants.PREF_LOGIN, Context.MODE_PRIVATE)
        val uName = sp.getString(Constants.PREF_USERNAME, null)
        val pass = sp.getString(Constants.PREF_PASSWORD, null)

        activityIntent = if (uName == null || pass == null) {
            Intent(this, LoginActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        finish()
        startActivity(activityIntent)
    }

}