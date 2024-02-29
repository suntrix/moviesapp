package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.endpoints

import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.SearchResponse
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.Type
import suntrix.kmp.moviesapp.shared.omdb.test.apiKey
import suntrix.kmp.moviesapp.shared.omdb.test.decodeFromFile
import suntrix.kmp.moviesapp.shared.omdb.test.mockedApiClient
import suntrix.kmp.moviesapp.shared.omdb.test.respondJsonFromFile
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class SearchTest {

    @Test
    fun `GIVEN mocked api client WHEN search is invoked with test data THEN expected data is returned`() {
        runBlocking {
            listOf(null).plus(Type.entries.toTypedArray()).forEach { type ->
                listOf(null, 2000).forEach { releaseYear ->
                    listOf(null, 2).forEach { page ->
                        val apiClient = mockedApiClient { request ->
                            when {
                                request.url.encodedPath.matches(Regex("""/""")) -> {
                                    assertEquals(HttpMethod.Get, request.method)
                                    assertEquals(apiKey, request.url.parameters["apikey"])

                                    assertEquals("qwerty", request.url.parameters["s"])
                                    type?.let { assertEquals(it.toString(), request.url.parameters["type"]) }
                                    releaseYear?.let { assertEquals(it.toString(), request.url.parameters["y"]) }
                                    page?.let { assertEquals(it.toString(), request.url.parameters["page"]) }

                                    respondJsonFromFile("search.json")
                                }

                                else -> fail()
                            }
                        }

                        assertEquals(
                            Json.decodeFromFile<SearchResponse>("search.json"),
                            apiClient.use { it.search("qwerty", type, releaseYear, page) }
                        )
                    }
                }
            }
        }
    }
}