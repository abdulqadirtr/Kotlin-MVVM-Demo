package com.example.demoarchitectcomponent.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "xkcd_table")
data class XKCDInitialDbResponseModel(
    val alt: String,
    val day: String,
    val img: String,
    val link: String,
    val month: String,
    val news: String,
    @PrimaryKey @ColumnInfo(name = "num")
    val num : Int,
    val safe_title: String,
    var title: String,
    val transcript: String,
    val year: String
)
