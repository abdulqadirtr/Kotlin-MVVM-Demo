package com.example.demoarchitectcomponent.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoarchitectcomponent.repository.XkcdRepository

class ViewModelFactory(private val xkcdRepository: XkcdRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(XkcdMainViewModel::class.java)) {
           XkcdMainViewModel(this.xkcdRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}