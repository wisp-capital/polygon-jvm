package io.polygon.kotlin.sdk.rest.crypto

import com.thinkinglogic.builder.annotation.Builder
import io.ktor.http.*
import kotlinx.serialization.Serializable

/** See [PolygonCryptoClient.getHistoricTradesBlocking] */
suspend fun PolygonCryptoClient.getHistoricTrades(params: HistoricCryptoTradesParameters): HistoricCryptoTradesDTO =
    polygonClient.fetchResult {
        path("v1", "historic", "crypto", params.from, params.to, params.date)

        parameters["limit"] = params.limit.toString()
        params.offset?.let { parameters["offset"] = it.toString() }
    }

@Builder
data class HistoricCryptoTradesParameters(
    val from: String,
    val to: String,

    /** Date of the historic trades to retrieve. Formatted YYYY-MM-DD */
    val date: String,

    /**
     * Timestamp offset, used for pagination. This is the offset at which to start the results.
     * Using the timestamp of the last result as the offset will give you the next page of results.
     */
    val offset: Long? = null,

    /** Limit the size of response, Max 10000 */
    val limit: Int
)

@Serializable
data class HistoricCryptoTradesDTO(
    val status: String? = null,
    val day: String? = null,
    val symbol: String? = null,
    val msLatency: Long? = null,
    val ticks: List<CryptoTickDTO>
)
