package com.example.spaceapp.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("app:src")
        fun setImageUri(view: ImageView, imageUri: Uri?) {
            if (imageUri != null) {
                view.setImageURI(imageUri)
            }
        }
    }
}