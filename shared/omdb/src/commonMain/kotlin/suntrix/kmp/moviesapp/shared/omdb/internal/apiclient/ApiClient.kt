package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.BrowserUserAgent
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kmp.common.ktor.KtorHttpClient
import kmp.common.ktor.httpClientEngine
import kotlinx.serialization.SerializationException
import suntrix.kmp.moviesapp.shared.omdb.BuildKonfig
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.model.ErrorResponse

internal class ApiClient(
    private val apiKey: String = BuildKonfig.API_KEY,
    baseUrl: Url = Url("https://www.omdbapi.com"),
    engine: HttpClientEngine = httpClientEngine(),
) : KtorHttpClient(engine) {

    override val setupHttpClient: HttpClientConfig<*>.() -> Unit = {
        install(ContentNegotiation) { json() }

//        Logging {
//            level = if (BuildKonfig.DEBUG) LogLevel.ALL else LogLevel.INFO
//        }

        BrowserUserAgent()

        HttpResponseValidator {
            validateResponse { response ->
                try {
                    val error: ErrorResponse = DefaultJson.decodeFromString(response.readBytes().contentToString())
                    throw ErrorResponseException(response, error)
                } catch (error: SerializationException) {
                    // nothing to do
                }
            }
        }

        defaultRequest {
            host = baseUrl.host
            contentType(ContentType.Application.Json)
            url {
                protocol = baseUrl.protocol

                parameters.append("apikey", apiKey)
            }
        }
    }
}

internal class ErrorResponseException(
    response: HttpResponse,
    val errorResponse: ErrorResponse
) : ResponseException(response, errorResponse.error)