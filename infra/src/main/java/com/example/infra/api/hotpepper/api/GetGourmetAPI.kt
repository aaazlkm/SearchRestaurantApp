package com.example.infra.api.hotpepper.api

import com.example.infra.api.hotpepper.HotpepperAPIConfiguration
import com.example.infra.api.hotpepper.HotpepperAPIPath
import com.example.infra.api.hotpepper.model.ResultsData

object GetGourmetAPI {
    data class Request(
        val lat: Double,
        val lng: Double,
        val range: Int = 1,
        val start: Int = 1,
        val count: Int = 10,
    ) {
        enum class QueryName(val queryName: String) {
            KEY("key"),
            LAT("lat"),
            LNG("lng"),
            RANGE("range"),
            START("start"),
            COUNT("count"),
            FORMAT("format"),
        }

        val path = HotpepperAPIPath.GET_GOURMET

        val queryParameter: Map<String, String>
            get() = mapOf(
                QueryName.KEY.queryName to HotpepperAPIConfiguration.HOTPEPPER_API_KEY,
                QueryName.LAT.queryName to lat.toString(),
                QueryName.LNG.queryName to lng.toString(),
                QueryName.RANGE.queryName to range.toString(),
                QueryName.START.queryName to start.toString(),
                QueryName.COUNT.queryName to count.toString(),
                QueryName.FORMAT.queryName to HotpepperAPIConfiguration.format,
            )
    }

    data class Response(
        val results: ResultsData
    )
}
