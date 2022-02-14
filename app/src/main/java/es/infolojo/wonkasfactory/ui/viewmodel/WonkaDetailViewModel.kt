package es.infolojo.wonkasfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.wonkasfactory.data.Mappers.toVo
import es.infolojo.wonkasfactory.data.adapters.WonkaDetailState
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO
import es.infolojo.wonkasfactory.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WonkaDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _wonkaDetailState = MutableLiveData<WonkaDetailState>()
    val wonkaDetailState: LiveData<WonkaDetailState>
        get() = _wonkaDetailState

    private var wonka: WonkaWorkerBO? = null

    fun init(id: String) {
        _wonkaDetailState.postValue(WonkaDetailState.Loading)
        getWonka(id)
    }

    private fun getWonka(id: String) {
        viewModelScope.launch {
            wonka = repository.getOneWonkaWorker(id)
            if (wonka != null) {
                wonka?.let {
                    _wonkaDetailState.postValue(WonkaDetailState.Render(it.toVo()))
                }
            } else {
                _wonkaDetailState.postValue(WonkaDetailState.Error)
            }
        }
    }
}