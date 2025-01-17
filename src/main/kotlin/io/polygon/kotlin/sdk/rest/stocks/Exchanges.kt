package io.polygon.kotlin.sdk.rest.stocks

import io.ktor.http.*
import kotlinx.serialization.Serializable

/** See [PolygonStocksClient.getSupportedExchangesBlocking] */
suspend fun PolygonStocksClient.getSupportedExchanges(): List<ExchangeDTO> =
    polygonClient.fetchResult { path("v1", "meta", "exchanges") }

@Serializable
data class ExchangeDTO(
    val id: Long? = null,
    val type: String? = null,
    val market: String? = null,
    val name: String? = null,
    val mic: String? = null,
    val tape: String? = null
)
