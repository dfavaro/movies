package com.danielefavaro.movies.base.ktx

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.danielefavaro.movies.R

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground) // TODO placeholder
        .into(this)
}