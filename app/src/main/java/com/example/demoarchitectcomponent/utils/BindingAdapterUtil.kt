package com.example.demoarchitectcomponent.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.demoarchitectcomponent.XkcdModel

@BindingAdapter("xkcd_name")
fun TextView.setXkcdName(item : XkcdModel?){
    item?.let {
        text = it.xkcdName
    }
}

@BindingAdapter("xkcd_link")
fun TextView.setLink(item: XkcdModel?){
    item?.let {
        text = it.xkcdUrl
    }
}