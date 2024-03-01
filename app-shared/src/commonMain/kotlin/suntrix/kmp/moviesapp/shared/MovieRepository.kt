package suntrix.kmp.moviesapp.shared

import suntrix.kmp.moviesapp.shared.logging.injectLogger
import suntrix.kmp.moviesapp.shared.omdb.injectOMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult

public interface MovieRepository {
    public suspend fun search(
        query: String,
        releaseYear: Int? = null,
        page: Int? = null
    ): List<SearchResult>
}

public fun injectMovieRepository(): MovieRepository = DefaultMovieRepository(
    omDbApiRepository = injectOMDbApiRepository(),
    logger = injectLogger()
)
