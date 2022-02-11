package es.infolojo.wonkasfactory.data.remote

import es.infolojo.wonkasfactory.data.remote.dto.WonkaWorkerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET
    suspend fun getAllWonkasWorkers(): Response<List<WonkaWorkerDTO>>

    @GET("/{id}")
    suspend fun getOneWonkaWorker(
        @Path("id") id: String
    ): Response<WonkaWorkerDTO?>

}