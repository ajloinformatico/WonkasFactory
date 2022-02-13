package es.infolojo.wonkasfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.wonkasfactory.data.Mappers.toVo
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO
import es.infolojo.wonkasfactory.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WonkasListViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {


    private val _wonkaListState = MutableLiveData<WonkasListState>()
    val wonkasListState: LiveData<WonkasListState>
        get() = _wonkaListState

    private var wonkas = mutableListOf<WonkaWorkerBO>()

    fun init() {
        _wonkaListState.postValue(WonkasListState.Loading)
        getWonkas()
    }

    private fun getWonkas() {
        viewModelScope.launch {
            wonkas = repository.getAllWonkasWorkers().toMutableList()
            if (wonkas.isNotEmpty()) {
                _wonkaListState.postValue(WonkasListState.Render(wonkas.map {it.toVo()}))
            } else {
                _wonkaListState.postValue(WonkasListState.Error)
            }
        }
    }

}