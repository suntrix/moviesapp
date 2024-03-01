package suntrix.kmp.moviesapp.shared

import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.omdb.OMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult
import suntrix.kmp.moviesapp.shared.omdb.model.Type

public class DefaultSeriesRepository(
    private val omDbApiRepository: OMDbApiRepository,
    private val logger: Logger
) : MovieRepository {
    init {
        logger.setup("suntrix.kmp.moviesapp.shared", "DefaultSeriesRepository")
    }

    override suspend fun search(
        query: String,
        releaseYear: Int?,
        page: Int?
    ): List<SearchResult> {
        logger.debug("search", mapOf("query" to query, "releaseYear" to "$releaseYear", "page" to "$page"))

        return omDbApiRepository.search(
            query = query,
            type = Type.SERIES,
            releaseYear = releaseYear,
            page = page
        ).also {
            logger.debug("search", mapOf("data" to "$it"))
        }
    }
}