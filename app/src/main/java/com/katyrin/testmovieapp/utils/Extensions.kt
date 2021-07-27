package com.katyrin.testmovieapp.utils

import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.katyrin.testmovieapp.R

fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

@Suppress("IMPLICIT_CAST_TO_ANY")
fun AppCompatImageView.setImageByUri(uri: String?, placeholder: Int = R.drawable.ic_no_photo_vector) {
    val glideUrl = if (uri.isNullOrEmpty()) placeholder else GlideUrl(uri)

    Glide.with(context)
        .load(glideUrl)
        .placeholder(placeholder)
        .error(R.drawable.ic_no_photo_vector)
        .into(this)
}