package es.infolojo.wonkasfactory.data.adapters

import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO

sealed class WonkasListState {
    data class Render(val wonkas: List<WonkaWorkerVO>): WonkasListState()

    object Loading: WonkasListState()
    object Error: WonkasListState()

}