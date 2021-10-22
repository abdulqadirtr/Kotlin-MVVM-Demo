package com.example.demoarchitectcomponent.repository

import com.example.demoarchitectcomponent.xkcdapi.XKCDNetwork

class XkcdRepository {
    suspend fun getComics() = XKCDNetwork.retrofit.getInitialComic()
}