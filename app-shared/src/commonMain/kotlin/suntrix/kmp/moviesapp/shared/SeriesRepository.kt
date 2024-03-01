package suntrix.kmp.moviesapp.shared

import suntrix.kmp.moviesapp.shared.logging.injectLogger
import suntrix.kmp.moviesapp.shared.omdb.injectOMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult

public interface SeriesRepository {
    public suspend fun search(
        query: String,
        releaseYear: Int? = null,
        page: Int? = null
    ): List<SearchResult>
}

public fun injectSeriesRepository(): MovieRepository = DefaultSeriesRepository(
    omDbApiRepository = injectOMDbApiRepository(),
    logger = injectLogger()
)
