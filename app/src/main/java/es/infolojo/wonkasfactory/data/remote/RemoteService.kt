package es.infolojo.wonkasfactory.data.remote

import es.infolojo.wonkasfactory.data.remote.dto.WonkaWorkerContainer
import es.infolojo.wonkasfactory.data.remote.dto.WonkaWorkerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("napptilus/oompa-loompas")
    suspend fun getAllWonkasWorkers(): Response<WonkaWorkerContainer>?

    @GET("napptilus/oompa-loompas/{id}")
    suspend fun getOneWonkaWorker(
        @Path("id") id: String
    ): Response<WonkaWorkerDTO?>

}