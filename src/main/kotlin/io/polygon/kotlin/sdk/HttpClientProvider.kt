package io.polygon.kotlin.sdk

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.Interceptor

interface HttpClientProvider {
    fun buildClient(): HttpClient

    fun getDefaultRestURLBuilder(): URLBuilder
}

/**
 * A default [HttpClientProvider] which provides an [HttpClient] powered by the [OkHttp] engine.
 *
 * For more details on the interceptors and the difference between an application interceptor and a network interceptor,
 * see OkHttp's documentation: https://square.github.io/okhttp/interceptors/
 */
open class DefaultOkHttpClientProvider
@JvmOverloads
constructor(
    private val applicationInterceptors: List<Interceptor> = emptyList(),
    private val networkInterceptors: List<Interceptor> = emptyList()
) : DefaultJvmHttpClientProvider() {

    override fun buildEngine(): HttpClientEngine =
        OkHttp.create {
            applicationInterceptors.forEach(::addInterceptor)
            networkInterceptors.forEach(::addNetworkInterceptor)
        }
}

/**
 * A default [HttpClientProvider] which provides an [HttpClient] powered by a configurable [HttpClientEngine] engine.
 * For a list of possible engines, see https://ktor.io/clients/http-client/engines.html
 *
 * Engine defaults to [OkHttp]
 *
 * @see DefaultOkHttpClientProvider
 */
open class DefaultJvmHttpClientProvider
@JvmOverloads
constructor(
    private val engine: HttpClientEngine = OkHttp.create()
) : HttpClientProvider {

    open fun buildEngine(): HttpClientEngine = engine

    override fun buildClient(): HttpClient =
        HttpClient(buildEngine()) {
            install(WebSockets)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

    override fun getDefaultRestURLBuilder(): URLBuilder =
        URLBuilder(
            protocol = URLProtocol.HTTPS,
            port = DEFAULT_PORT
        )
}
