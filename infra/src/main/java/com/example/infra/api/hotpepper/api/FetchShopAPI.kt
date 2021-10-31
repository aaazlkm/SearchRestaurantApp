package com.example.infra.api.hotpepper.api

import com.example.infra.api.hotpepper.HotpepperAPIPath
import com.example.infra.api.hotpepper.HotpepperRequest
import com.example.infra.api.hotpepper.HotpepperResponse
import com.example.infra.api.hotpepper.model.SearchResultsData

object FetchShopAPI {
    data class Request(
        val shopId: String
    ) : HotpepperRequest {
        enum class QueryName(val queryName: String) {
            ID("id"),
        }

        override val path = HotpepperAPIPath.FETCH_SHOPS

        override val optionQueryParameter: Map<String, String>
            get() = mapOf(
                QueryName.ID.queryName to shopId,
            )
    }

    data class Response(
        override val results: SearchResultsData
    ) : HotpepperResponse
}
