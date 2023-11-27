package com.example.base.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.base.R

object ImageLoader {

    fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView).load(imageUrl).into(imageView)
    }

    fun loadImage(imageView: ImageView, imageUrl: String, placeholder: Int = R.drawable.img) {
        Glide.with(imageView).load(imageUrl).placeholder(placeholder).into(imageView)
    }
}