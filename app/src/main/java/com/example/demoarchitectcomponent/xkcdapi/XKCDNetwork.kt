package com.example.demoarchitectcomponent.xkcdapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener


object XKCDNetwork {

    var okHttpClient = OkHttpClient()
    var httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

   /* HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(LoggingInterceptor.Level.BODY);
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
    .addInterceptor(loggingInterceptor)
    .build();*/

     val retrofit = Retrofit.Builder()
        .baseUrl("https://xkcd.com/")
        .addConverterFactory(GsonConverterFactory.create())
         .client(okHttpClient.newBuilder().addInterceptor(httpLoggingInterceptor).build())
        .build()
        .create(XKCDApiInterface::class.java)

}