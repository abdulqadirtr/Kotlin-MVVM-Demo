package com.example.demoarchitectcomponent.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class XKCDRoomViewModel(app: Application) : AndroidViewModel(app) {

    //ROOMDBVIEW MODEL FOR XKCD
    private val repository = XKCDRoomRepository(app)
    private val allNotes = repository.getAllComics()

    suspend fun insert(comic: XKCDInitialDbResponseModel) {
        repository.insert(comic)
    }

    fun update(comic: XKCDInitialDbResponseModel) {
        repository.update(comic)
    }

    fun delete(comic: XKCDInitialDbResponseModel) {
        repository.delete(comic)
    }

    fun deleteAllNotes() {
        repository.deleteAllComics()
    }

    fun getAllNotes(): LiveData<List<XKCDInitialDbResponseModel>> {
        return allNotes
    }
}