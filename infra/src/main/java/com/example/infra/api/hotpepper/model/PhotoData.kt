package com.example.infra.api.hotpepper.model

data class PhotoData(
    val mobile: MobileData,
)

data class MobileData(
    val l: String,
    val s: String,
)
