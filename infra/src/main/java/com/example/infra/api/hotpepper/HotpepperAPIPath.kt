package com.example.infra.api.hotpepper

enum class HotpepperAPIPath {
    GET_GOURMET;

    val value: String
        get() = "gourmet/v1/"
}
