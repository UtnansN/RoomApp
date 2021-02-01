package com.example.roomapp

import android.view.View
import android.widget.TextView

object Utils {

        fun setTextOrViewGoneIfBlank(view: TextView, txt: String?) {
            if (txt.isNullOrBlank()) {
                view.visibility = View.GONE
            }
            else {
                view.text = txt
            }
        }

        fun setViewGoneIfBlank(view: View, txt: String?) {
            if (txt.isNullOrBlank()) {
                view.visibility = View.GONE
            }
        }

}