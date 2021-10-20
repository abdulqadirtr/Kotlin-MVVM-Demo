package com.example.demoarchitectcomponent.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){

    private val _counterValue = MutableLiveData<Int>()
    val counterValue: LiveData<Int> get() = _counterValue


    init {
       // _counterValue.value = 0
    }

     fun getValue(){
        _counterValue.value = +2
    }

}