package com.example.spaceapp.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject
import android.text.format.DateFormat as AndroidDateFormat

// I hate time.
class DateTimeConverter @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH)
    private val timeFormat = AndroidDateFormat.getTimeFormat(appContext)

    // Makes me cringe but works. TODO: Improve?
    fun instantToLocalDateAndTimeString(str: String): String {
        val zdt = ZonedDateTime.ofInstant(Instant.parse(str), ZoneId.systemDefault())
        val date = Date.from(zdt.toInstant())

        val formattedTime = timeFormat.format(date)
        val formattedDate = dateFormat.format(date)

        return "$formattedDate $formattedTime"
    }

    fun createDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return dateFormat.format(calendar.time)
    }

    fun createTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return timeFormat.format(calendar.time)
    }

}