package suntrix.kmp.moviesapp.shared.omdb.internal

import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ApiClient
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.endpoints.search
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.SearchResponse
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.Type

class OMDbApiDataSource(
    private val apiClient: ApiClient = ApiClient()
) {
    suspend fun search(
        query: String,
        type: Type? = null,
        releaseYear: Int? = null,
        page: Int? = null,
    ): SearchResponse = apiClient.search(query, type, releaseYear, page)
}