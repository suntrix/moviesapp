package suntrix.kmp.moviesapp.shared.omdb

import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult

interface OMDbApiRepository {
    suspend fun search(query: String): List<SearchResult>
}