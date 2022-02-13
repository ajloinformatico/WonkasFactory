package es.infolojo.wonkasfactory.data.adapters

import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO

sealed class WonkaDetailState {

    data class Render(val wonka: WonkaWorkerVO): WonkaDetailState()

    object Error: WonkaDetailState()
    object Loading: WonkaDetailState()
}