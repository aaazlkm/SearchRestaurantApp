package com.example.infra.api.hotpepper

enum class HotpepperAPIPath {
    FETCH_SHOPS;

    val value: String
        get() = "gourmet/v1/"
}
