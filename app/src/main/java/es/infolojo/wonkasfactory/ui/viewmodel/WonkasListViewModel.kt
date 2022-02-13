package es.infolojo.wonkasfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.wonkasfactory.data.Mappers.toVo
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.bo.ORDER_STATE
import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO
import es.infolojo.wonkasfactory.data.repository.Repository
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
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

    fun orderByName(orderState: ORDER_STATE) {
        when (orderState) {
            ORDER_STATE.BY_NAME -> {
                wonkas = wonkas.toList().sortedBy { it.firstName }.toMutableList()
            }
            ORDER_STATE.BY_AGE -> {
                wonkas = wonkas.toList().sortedBy { it.age.toInt() }.reversed().toMutableList()
            }
            ORDER_STATE.BY_HEIGHT -> {
                wonkas = wonkas.sortedBy { it.height.toInt() }.reversed().toMutableList()
            }
        }
        _wonkaListState.postValue(WonkasListState.Render(wonkas.map { it.toVo() }))
    }

    fun deleteWonka(id: String) {
        wonkas = wonkas.toList().filter { it.id != id }.toMutableList()
        _wonkaListState.postValue(WonkasListState.Render(wonkas.map { it.toVo() }))
    }

}