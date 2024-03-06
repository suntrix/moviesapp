package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.endpoints

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ApiClient
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ErrorResponseException
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.SearchResponse
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.Type
import kotlin.coroutines.cancellation.CancellationException

@Throws(ErrorResponseException::class, CancellationException::class)
internal suspend fun ApiClient.search(
    query: String,
    type: Type? = null,
    releaseYear: Int? = null,
    page: Int? = null,
): SearchResponse = httpClient.get("/") {
    url {
        parameter("s", query)
        type?.let { parameter("type", it) }
        releaseYear?.let { parameter("y", it) }
        page?.let { parameter("page", it) }
    }
}.body()
