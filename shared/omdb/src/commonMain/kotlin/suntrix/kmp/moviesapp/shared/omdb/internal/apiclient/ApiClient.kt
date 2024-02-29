package suntrix.kmp.moviesapp.shared.omdb.internal.apiclient

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.BrowserUserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kmp.common.ktor.KtorHttpClient
import kmp.common.ktor.httpClientEngine
import suntrix.kmp.moviesapp.shared.omdb.BuildKonfig

class ApiClient(
    private val apiKey: String = BuildKonfig.API_KEY,
    baseUrl: Url = Url("https://www.omdbapi.com"),
    engine: HttpClientEngine = httpClientEngine(),
) : KtorHttpClient(engine) {

    override val setupHttpClient: HttpClientConfig<*>.() -> Unit = {
        install(ContentNegotiation) {
            json()
        }

        Logging {
            level = LogLevel.INFO
        }

        BrowserUserAgent()

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