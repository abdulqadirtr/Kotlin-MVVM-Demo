package com.example.demoarchitectcomponent.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demoarchitectcomponent.repository.XkcdDao
import com.example.demoarchitectcomponent.xkcdapi.XKCDInitialResponseModel
import com.example.demoarchitectcomponent.xkcdapi.XKCDNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class XkcdRepository {
    suspend fun getComics() = XKCDNetwork.retrofit.getInitialComic()
    suspend fun getAllComics(comics_id :Long ) = XKCDNetwork.retrofit.getAllComics(comics_id)
}




class XKCDRoomRepository(application: Application) {

    private var xkcdDao: XkcdDao
    private var allComics: LiveData<List<XKCDInitialDbResponseModel>>

    private val database = XkcdDatabase.getInstance(application)

    init {
        xkcdDao = database.xkcdDao()
        allComics = xkcdDao.getAllComic()
    }

   suspend fun insert(comic: XKCDInitialDbResponseModel) {
        withContext(Dispatchers.IO) {              // Dispatchers.IO (main-safety block)
            /* perform network IO here */          // Dispatchers.IO (main-safety block)
            xkcdDao.insert(comic)
        }

    }

    fun update(comic: XKCDInitialDbResponseModel) {
        xkcdDao.update(comic)
    }

    fun delete(comic: XKCDInitialDbResponseModel) {
        xkcdDao.delete(comic)
    }

    fun deleteAllComics() {
        xkcdDao.deleteAllComics()

    }

    fun getAllComics(): LiveData<List<XKCDInitialDbResponseModel>> {
        return allComics
    }



}