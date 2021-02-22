package com.example.spaceapp.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.spaceapp.R

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("app:src", "placeholder", requireAll = false)
        fun setImageUri(view: ImageView, imageUri: Uri?, placeholder: Drawable?) {
            when {
                imageUri != null -> {
                    view.setImageURI(imageUri)
                }
                placeholder != null -> {
                    view.setImageDrawable(placeholder)
                }
                else -> {
                    view.setImageDrawable(null)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadUserImageWithGlide(view: ImageView, imageUrl: String?) {
            if (imageUrl != null) {
                Glide.with(view.context)
                    .load("https://picsum.photos/200")
                    .placeholder(R.drawable.ic_userplaceholder)
                    .error(R.drawable.ic_userplaceholder)
                    .circleCrop()
                    .into(view)
            } else {
                view.setImageResource(R.drawable.ic_userplaceholder)
            }
        }

    }
}