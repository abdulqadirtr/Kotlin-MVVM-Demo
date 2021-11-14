package com.example.demoarchitectcomponent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "xkcd_table")
 public final class XkcdModel {
    @PrimaryKey(autoGenerate = true)
    val xkcdId: Long
    val xkcdName: String
    val xkcdUrl: String


}