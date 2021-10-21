package com.example.demoarchitectcomponent.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoarchitectcomponent.xkcdapi.XKCDInitialResponseModel
import com.example.demoarchitectcomponent.xkcdapi.XKCDNetwork
import kotlinx.coroutines.launch

class XkcdMainViewModel : ViewModel() {
    val myResponse: MutableLiveData<XKCDInitialResponseModel> = MutableLiveData()
    val myResponseList: MutableLiveData<List<XKCDInitialResponseModel>> = MutableLiveData()

    fun getInitialComic() {
        viewModelScope.launch {
            myResponse.value = XKCDNetwork.retrofit.getInitialComic()
        }
    }

    fun getAllComics() {
        viewModelScope.launch {
            myResponseList.value = XKCDNetwork.retrofit.getAllComics()
        }
    }
}