package es.infolojo.wonkasfactory.data.adapters

sealed class WonkasListAdapterAction {
    data class DetailAction(val id: String): WonkasListAdapterAction()
    data class RemoveAction(val id: String): WonkasListAdapterAction()

    //TODO FUTURE ACTIONS ON ROWS
}