package com.example.infra.api.hotpepper.api

import com.example.infra.api.hotpepper.HotpepperAPIPath
import com.example.infra.api.hotpepper.HotpepperRequest
import com.example.infra.api.hotpepper.HotpepperResponse
import com.example.infra.api.hotpepper.model.SearchResultsData

object SearchNearShopsAPI {
    data class Request(
        val lat: Double,
        val lng: Double,
        val range: Int,
        val start: Int = 1,
        val count: Int = 10,
    ) : HotpepperRequest {
        enum class QueryName(val queryName: String) {
            LAT("lat"),
            LNG("lng"),
            RANGE("range"),
            START("start"),
            COUNT("count"),
        }

        override val path = HotpepperAPIPath.SEARCH_SHOPS

        override val optionQueryParameter: Map<String, String>
            get() = mapOf(
                QueryName.LAT.queryName to lat.toString(),
                QueryName.LNG.queryName to lng.toString(),
                QueryName.RANGE.queryName to range.toString(),
                QueryName.START.queryName to start.toString(),
                QueryName.COUNT.queryName to count.toString(),
            )
    }

    data class Response(
        override val results: SearchResultsData
    ) : HotpepperResponse
}
