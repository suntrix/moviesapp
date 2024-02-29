package suntrix.kmp.moviesapp.shared.omdb.test

import com.goncalossilva.resources.Resource
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*

fun MockRequestHandleScope.respondJsonFromFile(filePath: String) = respondJson(
    Resource("src/commonTest/resources/$filePath").readText()
)

fun MockRequestHandleScope.respondJson(jsonString: String) = respond(
    content = ByteReadChannel(jsonString),
    status = HttpStatusCode.OK,
    headers = headersOf(HttpHeaders.ContentType, "application/json")
)