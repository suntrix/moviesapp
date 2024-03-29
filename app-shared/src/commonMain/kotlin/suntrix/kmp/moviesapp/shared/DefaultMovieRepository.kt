package suntrix.kmp.moviesapp.shared

import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.omdb.OMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult
import suntrix.kmp.moviesapp.shared.omdb.model.Type

public class DefaultMovieRepository(
    private val omDbApiRepository: OMDbApiRepository,
    private val logger: Logger
) : MovieRepository {
    override suspend fun search(
        query: String,
        releaseYear: Int?,
        page: Int?
    ): List<SearchResult> {
        logger.debug("DefaultMovieRepository::search", mapOf("query" to query, "releaseYear" to "$releaseYear", "page" to "$page"))

        return omDbApiRepository.search(
            query = query,
            type = Type.MOVIE,
            releaseYear = releaseYear,
            page = page
        ).also {
            logger.debug("DefaultMovieRepository::search", mapOf("data" to "$it"))
        }
    }
}