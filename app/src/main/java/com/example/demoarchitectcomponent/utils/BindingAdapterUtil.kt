package com.example.demoarchitectcomponent.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel
import com.example.demoarchitectcomponent.viewModel.XkcdStatus

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

@BindingAdapter("xkcdStatus")
fun checkStatus(statusImage : ImageView, status: XkcdStatus){

    when(status){
        XkcdStatus.ERRO -> {
            statusImage.setImageResource(R.drawable.ic_connection_error)
            statusImage.visibility = View.VISIBLE
        }
        XkcdStatus.LOADING -> {
            statusImage.setImageResource(R.drawable.loading_animation)
            statusImage.visibility = View.VISIBLE
        }
        XkcdStatus.DONE -> {
            statusImage.visibility = View.GONE
        }
    }


}