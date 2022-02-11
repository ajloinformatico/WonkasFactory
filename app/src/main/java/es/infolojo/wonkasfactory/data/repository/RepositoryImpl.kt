package es.infolojo.wonkasfactory.data.repository

import es.infolojo.wonkasfactory.data.Mappers.toBO
import es.infolojo.wonkasfactory.data.Mappers.toBo
import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO
import es.infolojo.wonkasfactory.data.remote.RemoteService

class RepositoryImpl(
    private val remoteService: RemoteService
) : Repository {

    override suspend fun getAllWonkasWorkers(): List<WonkaWorkerBO> {
        val workers = mutableListOf<WonkaWorkerBO>()
        val resource = remoteService.getAllWonkasWorkers()

        if (resource.isSuccessful) {
            workers.addAll(
                resource.body().orEmpty().toBo()
            )
        }
        return workers

    }

    override suspend fun getOneWonkaWorker(id: String): WonkaWorkerBO {
        val resource = remoteService.getOneWonkaWorker(id)

        val defaultWorker = WonkaWorkerBO()

        return if (resource.isSuccessful) {
            resource.body()?.toBO()
            defaultWorker

        } else {
            defaultWorker
        }

    }
}