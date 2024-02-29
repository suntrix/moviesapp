package suntrix.kmp.moviesapp.shared.omdb.internal

import suntrix.kmp.moviesapp.shared.omdb.OMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult

class DefaultOMDbApiRepository(
    private val dataSource: OMDbApiDataSource = OMDbApiDataSource()
) : OMDbApiRepository {
    override suspend fun search(query: String): List<SearchResult> =
        dataSource.search(query).run {
            results.map {
                SearchResult(
                    it.title,
                    it.year,
                    it.poster
                )
            }
        }
}