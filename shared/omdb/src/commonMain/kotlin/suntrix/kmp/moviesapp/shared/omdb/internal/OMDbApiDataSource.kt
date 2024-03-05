package suntrix.kmp.moviesapp.shared.omdb.internal

import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ApiClient
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.endpoints.search
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.SearchResponse
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.Type

internal class OMDbApiDataSource(
    private val apiClient: ApiClient,
    private val logger: Logger
) {
    suspend fun search(
        query: String,
        type: Type? = null,
        releaseYear: Int? = null,
        page: Int? = null,
    ): SearchResponse {
        logger.debug("OMDbApiDataSource::search", mapOf("query" to query, "type" to "$type", "releaseYear" to "$releaseYear", "page" to "$page"))

        return apiClient.search(query, type, releaseYear, page).also {
            logger.debug("OMDbApiDataSource::search", mapOf("response" to "$it"))
        }
    }
}