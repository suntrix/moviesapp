package suntrix.kmp.moviesapp.shared.omdb.internal

import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.omdb.OMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult
import suntrix.kmp.moviesapp.shared.omdb.model.Type
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.Type as InternalType

internal class DefaultOMDbApiRepository(
    private val dataSource: OMDbApiDataSource,
    private val logger: Logger
) : OMDbApiRepository {
    override suspend fun search(
        query: String,
        type: Type?,
        releaseYear: Int?,
        page: Int?,
    ): List<SearchResult> {
        logger.debug("DefaultOMDbApiRepository::search", mapOf("query" to query, "type" to "$type", "releaseYear" to "$releaseYear", "page" to "$page"))

        return dataSource.search(
            query = query,
            type = when(type) {
                Type.MOVIE -> InternalType.MOVIE
                Type.SERIES -> InternalType.SERIES
                Type.EPISODE -> InternalType.EPISODE
                null -> null
            },
            releaseYear = releaseYear,
            page = page
        ).run {
            results.map {
                SearchResult(
                    it.title,
                    it.year,
                    it.poster
                )
            }
        }.also {
            logger.debug("DefaultOMDbApiRepository::search", mapOf("data" to "$it"))
        }
    }
}