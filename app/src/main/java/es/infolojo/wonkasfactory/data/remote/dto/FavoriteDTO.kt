package es.infolojo.wonkasfactory.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FavoriteDTO(
    @SerializedName("color") val color: String?,
    @SerializedName("food") val food: String?
): Serializable