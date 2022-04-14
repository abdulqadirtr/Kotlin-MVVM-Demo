package com.example.demoarchitectcomponent.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel

@BindingAdapter("xkcd_name")
fun TextView.setXkcdName(item : XKCDInitialDbResponseModel?){
    item?.let {
        text = it.title
    }
}

@BindingAdapter("xkcd_link")
fun TextView.setLink(item: XKCDInitialDbResponseModel?){
    item?.let {
        text = it.year
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: View,
              imageUrl: String?) {
    val image: ImageView = view as ImageView
    Glide.with(image.context)
        .load(imageUrl)
        .into(image)
}