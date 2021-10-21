package com.example.demoarchitectcomponent.xkcdapi;

import retrofit2.http.GET;

public interface XKCDApiInterface {

    @GET("info.0.json")
    suspend fun getInitialComic(): XKCDInitialResponseModel

    @GET("info.0.json")
    suspend fun getAllComics(): List<XKCDInitialResponseModel>
}
