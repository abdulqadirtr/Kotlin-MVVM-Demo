package com.example.demoarchitectcomponent.xkcdapi;

import retrofit2.Response
import retrofit2.http.GET;
import retrofit2.http.Path

interface XKCDApiInterface {

    @GET("info.0.json")
    suspend fun getInitialComic(): Response<XKCDInitialResponseModel>

    @GET("{comic_num}/info.0.json")
    suspend fun getAllComics(@Path("comic_num") comicId: Long): XKCDInitialResponseModel
}
