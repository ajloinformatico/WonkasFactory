package es.infolojo.wonkasfactory.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WonkaWorkerContainer(
    @SerializedName("results") val listWorkers: List<WonkaWorkerDTO>
): Serializable

data class WonkaWorkerDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("favorite") val favorite: FavoriteDTO?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("profession") val profession: String?,
    @SerializedName("quota") val quota: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("height") val height: Long?,
    @SerializedName("age") val age: Long?,
    @SerializedName("gender") val genre: String?,
    @SerializedName("email") val email: String?
): Serializable


