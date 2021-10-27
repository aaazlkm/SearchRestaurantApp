package com.example.infra.api.hotpepper

import com.example.infra.api.hotpepper.model.HotpepperResult

interface HotpepperRequest {
    enum class QueryName(val queryName: String) {
        KEY("key"),
        FORMAT("format"),
    }

    val path: HotpepperAPIPath

    val queryParameter: Map<String, String>
        get() = coreQueryParameter + optionQueryParameter

    val coreQueryParameter: Map<String, String>
        get() = mapOf(
            QueryName.KEY.queryName to HotpepperAPIConfiguration.HOTPEPPER_API_KEY,
            QueryName.FORMAT.queryName to HotpepperAPIConfiguration.format,
        )

    val optionQueryParameter: Map<String, String>
        get() = mapOf()
}

interface HotpepperResponse {
    val results: HotpepperResult
}
