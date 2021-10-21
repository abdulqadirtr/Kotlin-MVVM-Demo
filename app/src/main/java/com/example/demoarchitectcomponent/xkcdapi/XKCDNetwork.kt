package com.example.demoarchitectcomponent.xkcdapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object XKCDNetwork {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://xkcd.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(XKCDApiInterface::class.java)
    }
}