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

   /*

   A Job has a define set of states: New, Active, Completing, Completed, Cancelling and Cancelled.

We can’t access the states themselves, but we can access properties of a Job: isActive, isCancelled and isCompleted.

   Supervisor Job
    It is similar to a regular Job with the only exception that its children can fail independently of each other.

    A child’s failure or cancellation does not result in the supervisor’s job failing or affecting its other children,
     therefore a supervisor can create a unique policy for dealing with its children’s failures.

    A SupervisorJob only works as described when it’s part of a scope: either created using supervisorScope or CoroutineScope(SupervisorJob()).
     Passing a SupervisorJob as a parameter of a coroutine builder will not have the desired effect you would’ve thought for cancellation
  */

   fun test6() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            supervisorScope {
                repeat(2) { i ->
                    try {
                        val result = async {

                        }
                          /*  doWork(i) }.await()
                            ("work result: ${result.toString()}")*/
                    } catch (t: Throwable) {
                      //  log("caught exception: $t")
                    }
                }
            }
        }
    }

}
