package com.saba.mypokeapp.util.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImageFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}
