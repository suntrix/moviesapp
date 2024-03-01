package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class Type {
    @SerialName("movie") MOVIE,
    @SerialName("series") SERIES,
    @SerialName("episode") EPISODE;

    override fun toString() = super.toString().lowercase()
}
