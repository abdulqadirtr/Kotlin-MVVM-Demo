package com.example.demoarchitectcomponent.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.xkcdapi.XKCDInitialResponseModel
import kotlinx.coroutines.launch

class XkcdMainViewModel(private val xkcdRepository: XkcdRepository) : ViewModel() {

    // Encapsulation
    private val _myResponse: MutableLiveData<XKCDInitialResponseModel> = MutableLiveData()
    val myResponse:LiveData<XKCDInitialResponseModel> =_myResponse

    fun getInitialComic() {
        viewModelScope.launch {
            _myResponse.value = xkcdRepository.getComics()
        }
    }

}