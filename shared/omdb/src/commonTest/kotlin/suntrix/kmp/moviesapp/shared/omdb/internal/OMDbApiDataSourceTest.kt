package suntrix.kmp.moviesapp.shared.omdb.internal

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ErrorResponseException
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.ErrorResponse
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.SearchResponse
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.Type
import suntrix.kmp.moviesapp.shared.omdb.test.decodeFromFile
import suntrix.kmp.moviesapp.shared.omdb.test.mockedApiClient
import suntrix.kmp.moviesapp.shared.omdb.test.respondJsonFromFile
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.fail

class OMDbApiDataSourceTest : TestsWithMocks() {

    override fun setUpMocks() = injectMocks(mocker)

    @Mock internal lateinit var logger: Logger

    private val subjectWithSuccess by withMocks {
        OMDbApiDataSource(
            apiClient = mockedApiClient { request ->
                when {
                    request.url.encodedPath.matches(Regex("""/""")) -> {
                        respondJsonFromFile("search.json")
                    }

                    else -> fail()
                }
            },
            logger = logger
        )
    }

    private val errorResponseException = ErrorResponseException(Json.decodeFromFile<ErrorResponse>("error.json"))

    private val subjectWithFailure by withMocks {
        OMDbApiDataSource(
            apiClient = mockedApiClient { request ->
                when {
                    request.url.encodedPath.matches(Regex("""/""")) -> {
                        throw errorResponseException
                    }

                    else -> fail()
                }
            },
            logger = logger
        )
    }

    @Test
    fun `GIVEN mocked api client WHEN search is invoked with test data THEN expected data is returned`() = runBlocking {
        val expectedData = Json.decodeFromFile<SearchResponse>("search.json")
        val queryString = "iron"

        listOf(null).plus(Type.entries.toTypedArray()).forEach { type ->
            listOf(null, 2000).forEach { releaseYear ->
                listOf(null, 2).forEach { page ->
                    every {
                        logger.debug(
                            functionName = isAny(),
                            params = isAny(),
                            message = isAny()
                        )
                    } returns Unit

                    assertEquals(
                        expectedData,
                        subjectWithSuccess.search(queryString, type, releaseYear, page)
                    )

                    verify {
                        logger.debug(
                            functionName = "OMDbApiDataSource::search",
                            params = mapOf(
                                "query" to queryString,
                                "type" to "$type",
                                "releaseYear" to "$releaseYear",
                                "page" to "$page"
                            )
                        )

                        logger.debug(
                            functionName = "OMDbApiDataSource::search",
                            params = mapOf(
                                "response" to "$expectedData"
                            )
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `GIVEN mocked api client WHEN search is invoked with test data THEN no data is returned`() = runBlocking {
        val expectedData = Json.decodeFromFile<ErrorResponse>("error.json")
        val queryString = "iron"

        listOf(null).plus(Type.entries.toTypedArray()).forEach { type ->
            listOf(null, 2000).forEach { releaseYear ->
                listOf(null, 2).forEach { page ->
                    every {
                        logger.debug(
                            functionName = isAny(),
                            params = isAny(),
                            message = isAny()
                        )
                    } returns Unit

                    every {
                        logger.error(
                            functionName = isAny(),
                            params = isAny(),
                            message = isAny(),
                            throwable = isAny()
                        )
                    } returns Unit

                    assertNull(subjectWithFailure.search(queryString, type, releaseYear, page))

                    verify {
                        logger.debug(
                            functionName = "OMDbApiDataSource::search",
                            params = mapOf("query" to queryString, "type" to "$type", "releaseYear" to "$releaseYear", "page" to "$page")
                        )

                        logger.error(
                            functionName = "OMDbApiDataSource::search",
                            params = mapOf("error" to "$expectedData"),
                            message = null,
                            throwable = errorResponseException.cause
                        )
                    }
                }
            }
        }
    }
}