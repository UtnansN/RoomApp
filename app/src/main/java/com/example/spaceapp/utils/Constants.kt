package com.example.spaceapp.utils

object Constants {

    // Actual machine's IP
    const val API_URL = "http://192.168.1.108:8080"

    const val PREF_LOGIN = "LOGIN"
    const val PREF_USERNAME = "username"
    const val PREF_PASSWORD = "password"
    const val PREF_TOKEN = "token"

    // DESTINATIONS
    const val DEST_SPACES = "/api/spaces"
    const val DEST_EXACT_SPACE = "/api/spaces/{code}"
    const val DEST_EVENTS_IN_SPACE = "/api/spaces/{spaceCode}/events"
    const val DEST_LOGIN = "/api/login"
    const val DEST_REGISTER = "/api/signup"
}