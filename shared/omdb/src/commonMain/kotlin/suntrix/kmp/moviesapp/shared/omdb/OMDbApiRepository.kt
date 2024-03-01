package suntrix.kmp.moviesapp.shared.omdb

import suntrix.kmp.moviesapp.shared.logging.injectLogger
import suntrix.kmp.moviesapp.shared.omdb.internal.DefaultOMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.internal.OMDbApiDataSource
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ApiClient
import suntrix.kmp.moviesapp.shared.omdb.model.SearchResult
import suntrix.kmp.moviesapp.shared.omdb.model.Type

public interface OMDbApiRepository {
    public suspend fun search(
        query: String,
        type: Type? = null,
        releaseYear: Int? = null,
        page: Int? = null,
    ): List<SearchResult>
}

public fun injectOMDbApiRepository(): OMDbApiRepository = DefaultOMDbApiRepository(
    dataSource  = OMDbApiDataSource(
        apiClient  = ApiClient(),
        logger = injectLogger()
    ),
    logger = injectLogger()
)