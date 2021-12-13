package com.example.demoarchitectcomponent.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel
import com.example.demoarchitectcomponent.room.XKCDRoomRepository
import com.example.demoarchitectcomponent.xkcdapi.XKCDInitialResponseModel
import kotlinx.coroutines.launch
import java.lang.Exception

class XkcdMainViewModel(private val xkcdRepository: XkcdRepository) : ViewModel() {

    // Encapsulation
    private val _myResponse: MutableLiveData<XKCDInitialResponseModel> = MutableLiveData()
    val myResponse: LiveData<XKCDInitialResponseModel> = _myResponse

    private val _allComicsResponse: MutableLiveData<XKCDInitialResponseModel> = MutableLiveData()
    val allComicsResponse: LiveData<XKCDInitialResponseModel> = _allComicsResponse

    fun getInitialComic() {
        viewModelScope.launch {
            _myResponse.value = xkcdRepository.getComics()
        }
    }

    fun getAllComics(comics_id: Long) {
        viewModelScope.launch {
            try {
                _allComicsResponse.value = xkcdRepository.getAllComics(comics_id)
            }
            catch (e:Exception){

            }
        }
    }

}



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