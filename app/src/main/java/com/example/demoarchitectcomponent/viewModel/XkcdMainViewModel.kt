package com.example.demoarchitectcomponent.viewModel

import androidx.lifecycle.*
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.xkcdapi.XKCDInitialResponseModel
import kotlinx.coroutines.*
import java.lang.Exception

class XkcdMainViewModel(private val xkcdRepository: XkcdRepository) : ViewModel() {

    private val _allComicsResponse: MutableLiveData<XKCDInitialResponseModel> = MutableLiveData()
    val allComicsResponse: LiveData<XKCDInitialResponseModel> = _allComicsResponse

    private val _currentComicsResponse: MutableLiveData<XKCDInitialResponseModel> = MutableLiveData()
    val currentComicsResponse: LiveData<XKCDInitialResponseModel> = _currentComicsResponse

    fun getAllComics(comics_id: Long) {
        viewModelScope.launch {
            try {
                _allComicsResponse.value = xkcdRepository.getAllComics(comics_id)
            } catch (e: Exception) {

            }
        }
    }

    fun getFirstComic() {
        viewModelScope.launch {
            try {
                val response = xkcdRepository.getComics()
                response.isSuccessful.let { isSuccess ->
                    if(isSuccess){
                        _currentComicsResponse.value = response.body()
                    }

                }
            } catch (e: Exception) {

            }
        }

    }

}
