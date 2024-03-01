package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchResponse(
    @SerialName("Search") val results: List<SearchResult>,
    val totalResults: Int,
    @SerialName("Response") val response: Boolean,
    @SerialName("Error") val error: String? = null
) {
    @Serializable
    data class SearchResult(
        @SerialName("Title") val title: String,
        @SerialName("Year") val year: String,
        val imdbID: String,
        @SerialName("Type") val type: Type,
        @SerialName("Poster") val poster: String
    )
}