package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorResponse(
    @SerialName("Response") val response: Boolean,
    @SerialName("Error") val error: String
)