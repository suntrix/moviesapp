package suntrix.kmp.moviesapp.shared.omdb.test

import com.goncalossilva.resources.Resource
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ApiClient

val apiKey = "qwerty123"

internal fun mockedApiClient(
    handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData
) = ApiClient(apiKey = apiKey, engine = MockEngine(handler))

inline fun <reified T> Json.decodeFromFile(filePath: String) =
    decodeFromString<T>(Resource("src/commonTest/resources/$filePath").readText())