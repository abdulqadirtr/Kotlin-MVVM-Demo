package com.example.demoarchitectcomponent

import android.os.Parcelable
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel
import com.example.demoarchitectcomponent.room.XKCDRoomViewModel
import kotlinx.android.parcel.Parcelize


@Parcelize
data class XkcdModel(
    val xkcdId: Int,
    val xkcdName: String,
    val xkcdUrl: String,
 ): Parcelable

fun XKCDInitialDbResponseModel.mapToXKCDModel() = XkcdModel(
    this.num ,
    this.title,
    this.img
)