package com.example.pruebaamericamovil.ui

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?, context: Context) {
    url?.let {
        Picasso.with(context)
            .load(movieImagePathBuilder(url))
            .fit()
            .centerCrop()
            .into(this)
    }
}

private fun movieImagePathBuilder(imagePath: String): String? {
    return "https://image.tmdb.org/t/p/" +
            "w500" +
            imagePath
}