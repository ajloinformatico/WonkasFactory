package es.infolojo.wonkasfactory.data.repository

import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO

interface Repository {

    suspend fun getAllWonkasWorkers(): List<WonkaWorkerBO>

    suspend fun getOneWonkaWorker(id: String): WonkaWorkerBO
}