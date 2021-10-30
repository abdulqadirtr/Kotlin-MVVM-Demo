package com.example.demoarchitectcomponent.repository

import com.example.demoarchitectcomponent.xkcdapi.XKCDNetwork

class XkcdRepository {
    suspend fun getComics() = XKCDNetwork.retrofit.getInitialComic()
    suspend fun getAllComics(comics_id :Long ) = XKCDNetwork.retrofit.getAllComics(comics_id)
}