package es.infolojo.wonkasfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.wonkasfactory.data.adapters.WonkaDetailState
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.repository.Repository
import javax.inject.Inject

//@HiltViewModel
class WonkaDetailViewModel @Inject constructor(
    private val repository: Repository
){

    private val _wonkaDetailState = MutableLiveData<WonkaDetailState>()
    val wonkaDetailState: LiveData<WonkaDetailState>
        get() = _wonkaDetailState

    fun init(id: String) {
        _wonkaDetailState.postValue(WonkaDetailState.Loading)
    }
}